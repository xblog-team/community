package com.xblog.community.user_role.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 사용자 권한 정보를 담는 응답 DTO(Data Transfer Object) 클래스입니다.
 * 특정 사용자의 권한(role)만을 반환할 때 사용됩니다.
 * 
 * <p>필드:</p>
 * <ul>
 *   <li><b>authority</b>: 사용자에게 할당된 권한 이름을 나타냅니다.</li>
 * </ul>
 * 
 * 어노테이션:
 * <ul>
 *   <li>{@code @Getter} - 필드의 Getter 메서드를 생성합니다.</li>
 *   <li>{@code @Setter} - 필드의 Setter 메서드를 생성합니다.</li>
 *   <li>{@code @NoArgsConstructor} - 기본 생성자를 생성합니다.</li>
 * </ul>
 */
@Getter
@Setter
@NoArgsConstructor
public class UserRoleOnlyResponseDto {
    private String authority;
}