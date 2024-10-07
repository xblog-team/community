package com.xblog.community.user_party.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 사용자와 파티 간의 관계를 나타내는 엔티티 클래스입니다.
 * 이 클래스는 특정 사용자가 특정 파티의 구성원으로서 어떤 역할을 수행하는지를 정의합니다.
 * 데이터베이스의 `user_party` 테이블과 매핑됩니다.
 * 
 * <p>필드 설명:</p>
 * <ul>
 *   <li><b>userPartyId</b>: 사용자 파티 관계의 고유 식별자 (자동 생성).</li>
 *   <li><b>userId</b>: 파티에 속하는 사용자의 ID.</li>
 *   <li><b>partyId</b>: 사용자가 속한 파티의 ID.</li>
 *   <li><b>role</b>: 사용자의 역할 (예: 관리자, 구성원).</li>
 *   <li><b>isRemoved</b>: 사용자가 파티에서 제거되었는지를 나타내는 플래그. 기본값은 false입니다.</li>
 *   <li><b>isAccepted</b>: 파티 초대가 수락되었는지를 나타내는 플래그. 기본값은 false입니다.</li>
 * </ul>
 * 
 * 어노테이션:
 * <ul>
 *   <li>{@code @Entity} - JPA 엔티티로 설정합니다.</li>
 *   <li>{@code @Table(name = "user_party")} - 데이터베이스의 `user_party` 테이블과 매핑합니다.</li>
 *   <li>{@code @Id} - 이 필드를 기본 키로 설정합니다.</li>
 *   <li>{@code @GeneratedValue(strategy = GenerationType.IDENTITY)} - 자동 생성 전략을 설정합니다.</li>
 *   <li>{@code @Column(name = "user_party_id")} - 테이블의 `user_party_id` 컬럼과 매핑합니다.</li>
 *   <li>{@code @Enumerated(EnumType.STRING)} - role 필드를 문자열로 매핑합니다.</li>
 *   <li>{@code @NoArgsConstructor(access = AccessLevel.PROTECTED)} - 기본 생성자를 생성하되, 접근 제한자를 보호로 설정합니다.</li>
 *   <li>{@code @Builder} - 빌더 패턴을 사용하여 객체 생성을 지원합니다.</li>
 * </ul>
 * 
 * <p>내부 열거형:</p>
 * <ul>
 *   <li>{@link Role} - 사용자의 역할을 정의하는 열거형. MANAGER 및 MEMBER 역할이 있습니다.</li>
 * </ul>
 */
@Entity
@Table(name = "user_party")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_party_id")
    private Long userPartyId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "party_id")
    private Long partyId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_removed")
    private Boolean isRemoved = false;

    @Column(name = "is_accepted")
    private Boolean isAccepted = false;

    @Builder
    public UserParty(String userId, Long partyId, Role role){
        this.userId = userId;
        this.partyId = partyId;
        this.role = role;
    }

    public enum Role{
        MANAGER,
        MEMBER
    }
}