package com.xblog.community.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사용자 등록 응답 DTO(Data Transfer Object)입니다.
 * 이 클래스는 사용자가 성공적으로 등록된 후 반환되는 정보를 담고 있습니다.
 * 
 * <p>주요 필드:</p>
 * <ul>
 *   <li>{@code userId} - 등록된 사용자의 고유 식별자입니다.</li>
 *   <li>{@code nickname} - 등록된 사용자의 닉네임입니다.</li>
 * </ul>
 */
@Getter
@AllArgsConstructor
public class RegisterUserResponseDto {
    private String userId;
    private String nickname;
}