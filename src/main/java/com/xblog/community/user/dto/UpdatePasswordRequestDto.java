package com.xblog.community.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 비밀번호 업데이트 요청 DTO(Data Transfer Object)입니다.
 * 이 클래스는 사용자가 비밀번호를 변경할 때 필요한 기존 비밀번호와 새 비밀번호 정보를 담고 있습니다.
 * 
 * <p>주요 필드:</p>
 * <ul>
 *   <li>{@code oldPassword} - 사용자의 기존 비밀번호로, 비밀번호 변경 시 인증을 위해 필요합니다.</li>
 *   <li>{@code newPassword} - 사용자가 설정할 새 비밀번호입니다.</li>
 * </ul>
 */
@Getter
@AllArgsConstructor
public class UpdatePasswordRequestDto {
    private String oldPassword;
    private String newPassword;
}