package com.xblog.community.user.service;

import com.xblog.community.user.dto.LoginInfoResponseDto;
import com.xblog.community.user.dto.RegisterUserRequestDto;
import com.xblog.community.user.dto.RegisterUserResponseDto;
import com.xblog.community.user.dto.UpdatePasswordRequestDto;

public interface UserService {
    LoginInfoResponseDto getLoginInfo(String userId);

    String getUserNickname(String userId);

    Boolean userExists(String userId);
    RegisterUserResponseDto createUser(RegisterUserRequestDto registerUserRequestDto);

    void withdrawUser(String userId);
    void disableOrEnableUser(String userId);

    void updatePassword(UpdatePasswordRequestDto updatePasswordRequestDto);

    void changeNickname(String userId, String newNickname);

    void changeAuthority(String userId, Short authorityId);
}
