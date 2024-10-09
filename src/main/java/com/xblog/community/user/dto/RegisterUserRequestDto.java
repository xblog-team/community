package com.xblog.community.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사용자 등록 요청 DTO(Data Transfer Object)입니다.
 * 이 클래스는 사용자의 ID, 비밀번호, 닉네임 및 권한 ID를 포함하여 사용자가 등록할 때 필요한 정보를 담고 있습니다.
 * 
 * <p>주요 필드:</p>
 * <ul>
 *   <li>{@code userId} - 사용자의 고유 식별자, 등록 시 중복되지 않아야 합니다.</li>
 *   <li>{@code password} - 사용자의 비밀번호, 보안상의 이유로 안전하게 저장해야 합니다.</li>
 *   <li>{@code nickname} - 사용자의 닉네임, 사용자에게 표시되는 이름입니다.</li>
 *   <li>{@code authorityId} - 사용자의 권한을 나타내는 ID, 권한 관리에 사용됩니다.</li>
 * </ul>
 */
@Getter
@AllArgsConstructor
public class RegisterUserRequestDto {
    private String userId;
    private String password;
    private String nickname;
    private Short authorityId;
}