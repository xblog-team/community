package com.xblog.community.user.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfoResponseDto {
    private String userId;
    private String userPw;
    private List<String> authorities;
}