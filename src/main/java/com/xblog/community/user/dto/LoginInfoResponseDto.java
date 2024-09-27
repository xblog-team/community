package com.xblog.community.user.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginInfoResponseDto {
    private String userId;
    private String password;
    private List<String> roles;
}