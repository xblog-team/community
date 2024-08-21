package com.xblog.community.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequestDto {
    private String userId;
    private String password;
    private String nickname;
    private Short authorityId;
}
