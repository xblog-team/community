package com.xblog.community.user.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사용자 로그인 정보 응답 DTO(Data Transfer Object)입니다.
 * 이 클래스는 사용자의 ID, 비밀번호 및 역할 목록을 포함합니다.
 * 
 * <p>주요 필드:</p>
 * <ul>
 *   <li>{@code userId} - 사용자의 고유 식별자</li>
 *   <li>{@code password} - 사용자의 비밀번호 (보안상의 이유로 일반적으로 클라이언트에 전송하지 않아야 합니다)</li>
 *   <li>{@code roles} - 사용자의 역할 목록</li>
 * </ul>
 */
@Getter
@AllArgsConstructor
public class LoginInfoResponseDto {
    private String userId;
    private String password;
    private List<String> roles;
}