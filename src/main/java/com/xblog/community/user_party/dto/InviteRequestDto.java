package com.xblog.community.user_party.dto;

import com.xblog.community.user_party.entity.UserParty.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 파티 초대 요청 정보를 담는 DTO(Data Transfer Object) 클래스입니다.
 * 이 클래스는 특정 사용자에게 파티 초대를 요청할 때 필요한 정보를 포함합니다.
 * 
 * <p>필드 설명:</p>
 * <ul>
 *   <li><b>userId</b>: 초대할 사용자의 ID.</li>
 *   <li><b>partyId</b>: 초대할 파티의 ID.</li>
 *   <li><b>role</b>: 초대할 사용자의 역할 (예: 관리자, 일반 사용자 등).</li>
 * </ul>
 * 
 * 어노테이션:
 * <ul>
 *   <li>{@code @AllArgsConstructor} - 모든 필드를 매개변수로 하는 생성자를 자동 생성합니다.</li>
 *   <li>{@code @Getter} - 필드의 Getter 메서드를 자동 생성합니다.</li>
 * </ul>
 */
@AllArgsConstructor
@Getter
public class InviteRequestDto {
    private String userId;
    private Long partyId;
    private Role role;
}