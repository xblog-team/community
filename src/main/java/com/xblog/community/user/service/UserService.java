package com.xblog.community.user.service;

import com.xblog.community.user.dto.LoginInfoResponseDto;

public interface UserService {
    LoginInfoResponseDto getLoginInfo(String userId);
}
