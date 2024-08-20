package com.xblog.community.user.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xblog.community.exception.UserAlreadyExistsException;
import com.xblog.community.exception.UserDoesNotExistException;
import com.xblog.community.user.dto.LoginInfoResponseDto;
import com.xblog.community.user.dto.RegisterUserRequestDto;
import com.xblog.community.user.dto.RegisterUserResponseDto;
import com.xblog.community.user.dto.UpdatePasswordRequestDto;
import com.xblog.community.user.entity.User;
import com.xblog.community.user.repository.UserRepository;
import com.xblog.community.user.service.UserService;
import com.xblog.community.user_role.entity.UserRole;
import com.xblog.community.user_role.repository.UserRoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public LoginInfoResponseDto getLoginInfo(String userId) {
        
        User user = userRepository.findById(userId).orElse(null);

        if(Objects.isNull(user)){
            return null;
        }

        return new LoginInfoResponseDto(user.getUserId(), user.getPassword(), userRoleRepository.getByUserId(userId));
    }

    @Override
    public Boolean userExists(String userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public RegisterUserResponseDto createUser(RegisterUserRequestDto registerUserRequestDto) {
        
        UserRole userRole = new UserRole();
        userRole.setAuthorityId(registerUserRequestDto.getAuthorityId());
        userRole.setUserId(registerUserRequestDto.getUserId());
        userRoleRepository.save(userRole);

        User user = new User();
        user.setUserId(registerUserRequestDto.getUserId());
        user.setNickname(registerUserRequestDto.getNickname());
        user.setPassword(passwordEncoder.encode(registerUserRequestDto.getPassword()));
        
        if (userExists(user.getUserId())) {
            throw new UserAlreadyExistsException();
        }

        userRepository.save(user);

        return new RegisterUserResponseDto(user.getUserId(),user.getNickname());
    }

    @Override
    public void withdrawUser(String userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (Objects.isNull(user)) {
            throw new UserDoesNotExistException();
        }

        user.setWithdraw(true);
        user.setWithdrawedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public void disableOrEnableUser(String userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (Objects.isNull(user)) {
            throw new UserDoesNotExistException();
        }
        
        if (user.getEnabled()) {
            user.setEnabled(false);
        }else{
            user.setEnabled(true);
        }
        userRepository.save(user);
    }

    @Override
    public void updatePassword(UpdatePasswordRequestDto updatePasswordRequestDto) {
        User user = userRepository.findById(updatePasswordRequestDto.getUserId()).orElse(null);

        if (Objects.isNull(user)) {
            throw new UserDoesNotExistException();
        }else if (user.getPassword().equals(updatePasswordRequestDto.getOldPassword())) {
            user.setPassword(updatePasswordRequestDto.getNewPassword());
            user.setPasswordChangeDate(LocalDateTime.now());
            userRepository.save(user);
        }
        
    }

    @Override
    public void changeNickname(String userId, String newNickname) {
        User user = userRepository.findById(userId).orElse(null);

        if (Objects.isNull(user)) {
            throw new UserDoesNotExistException();
        }
        
        user.setNickname(newNickname);
        userRepository.save(user);
    }

    @Override
    public void changeAuthority(String userId, Short authorityId) {
        UserRole userRole = userRoleRepository.findByUserId(userId);

        if (Objects.isNull(userRole)) {
            throw new UserDoesNotExistException();
        }
        
        userRole.setAuthorityId(authorityId);;
        userRoleRepository.save(userRole);
    }

}
