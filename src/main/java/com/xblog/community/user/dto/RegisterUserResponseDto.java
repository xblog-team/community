package com.xblog.community.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterUserResponseDto {
    private String userId;
    private String nickname;
}
