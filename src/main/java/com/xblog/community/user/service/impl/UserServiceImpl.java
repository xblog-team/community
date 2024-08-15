package com.xblog.community.user.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.xblog.community.user.dto.LoginInfoResponseDto;
import com.xblog.community.user.entity.User;
import com.xblog.community.user.repository.UserRepository;
import com.xblog.community.user.service.UserService;
import com.xblog.community.user_role.repository.UserRoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    @Override
    public LoginInfoResponseDto getLoginInfo(String userId) {
        
        User user = userRepository.findById(userId).orElse(null);

        if(Objects.isNull(user)){
            return null;
        }

        return new LoginInfoResponseDto(user.getUserId(), user.getPassword(), userRoleRepository.findByUserId(userId));
    }

}
