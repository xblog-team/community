package com.xblog.community.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterUserRequestDto {
    private String userId;
    private String password;
    private String nickname;
    private Short authorityId;
}
