package com.xblog.community.user.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xblog.community.authority.entity.Authority;
import com.xblog.community.authority.repository.AuthorityRepository;
import com.xblog.community.exception.IncorrectPassword;
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
    
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @Override
    public LoginInfoResponseDto getLoginInfo(String userId) {
        
        User user = userRepository.findById(userId).orElseThrow(() -> new UserDoesNotExistException());
        
        List<UserRole> userRoles = userRoleRepository.getByUserId(userId);

        List<String> roleList = userRoles.stream()
        .map(userRole -> authorityRepository.findById(userRole.getAuthorityId())
            .map(Authority::getAuthority)
            .orElseThrow(() -> new IllegalStateException("권한 존재하지 않습니다.")))
        .collect(Collectors.toList());
        
        return new LoginInfoResponseDto(user.getUserId(), user.getPassword(), roleList);
    }

    @Override
    public Boolean userExists(String userId) {
        return userRepository.existsById(userId);
    }
    
    @Transactional
    @Override
    public RegisterUserResponseDto createUser(RegisterUserRequestDto registerUserRequestDto) {
        
        if (userExists(registerUserRequestDto.getUserId())) {
            throw new UserAlreadyExistsException();
        }
        
        User user = new User();
        user.setUserId(registerUserRequestDto.getUserId());
        user.setNickname(registerUserRequestDto.getNickname());
        user.setPassword(passwordEncoder.encode(registerUserRequestDto.getPassword()));
        userRepository.save(user);

        UserRole userRole = new UserRole();
        userRole.setAuthorityId(registerUserRequestDto.getAuthorityId());
        userRole.setUserId(registerUserRequestDto.getUserId());
        userRoleRepository.save(userRole);
        
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
        }else if (passwordEncoder.matches(updatePasswordRequestDto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(updatePasswordRequestDto.getNewPassword()));
            user.setPasswordChangeDate(LocalDateTime.now());
            userRepository.save(user);
        }else{
            throw new IncorrectPassword();
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
        UserRole userRole = userRoleRepository.findByUserId(userId).orElse(null);

        if (Objects.isNull(userRole)) {
            throw new UserDoesNotExistException();
        }
        
        userRole.setAuthorityId(authorityId);;
        userRoleRepository.save(userRole);
    }

    @Override
    public String getUserNickname(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserDoesNotExistException()).getNickname();
    }

}
