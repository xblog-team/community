package com.xblog.community.user.service.impl;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xblog.community.exception.UserAlreadyExistsException;
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

        return new LoginInfoResponseDto(user.getUserId(), user.getPassword(), userRoleRepository.findByUserId(userId));
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withdrawUser'");
    }

    @Override
    public void disableUser(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disableUser'");
    }

    @Override
    public void updatePassword(UpdatePasswordRequestDto updatePasswordRequestDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePassword'");
    }

    @Override
    public void changeNickname(String userId, String newNickname) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeNickname'");
    }

    @Override
    public void changeAuthority(String userId, Short authorityId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeAuthority'");
    }

}
