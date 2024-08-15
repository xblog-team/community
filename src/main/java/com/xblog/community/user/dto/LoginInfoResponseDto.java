package com.xblog.community.user.dto;

import java.util.List;

import com.xblog.community.user_role.dto.UserRoleOnlyResponseDto;

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
    private List<UserRoleOnlyResponseDto> authorities;
}