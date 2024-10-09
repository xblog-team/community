package com.xblog.community.user_role.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 사용자 역할(User Role)을 나타내는 엔티티 클래스입니다.
 * 이 클래스는 사용자와 권한 간의 관계를 정의하며, 데이터베이스의 
 * `user_role` 테이블과 매핑됩니다.
 * 
 * <p>필드 설명:</p>
 * <ul>
 *   <li><b>userRoleId</b>: 사용자 역할의 고유 식별자 (자동 생성).</li>
 *   <li><b>userId</b>: 사용자 ID로, 특정 사용자를 식별합니다.</li>
 *   <li><b>authorityId</b>: 권한 ID로, 사용자가 할당받은 권한을 나타냅니다.</li>
 * </ul>
 * 
 * 어노테이션:
 * <ul>
 *   <li>{@code @Entity} - JPA 엔티티로 설정합니다.</li>
 *   <li>{@code @Table(name = "user_role")} - 데이터베이스의 `user_role` 테이블과 매핑합니다.</li>
 *   <li>{@code @Id} - 이 필드를 기본 키로 설정합니다.</li>
 *   <li>{@code @GeneratedValue(strategy = GenerationType.IDENTITY)} - 자동 생성 전략을 설정합니다.</li>
 *   <li>{@code @Column(name = "user_role_id")} - 테이블의 `user_role_id` 컬럼과 매핑합니다.</li>
 *   <li>{@code @NoArgsConstructor} - 기본 생성자를 생성합니다.</li>
 *   <li>{@code @Getter, @Setter} - Lombok을 사용하여 Getter 및 Setter 메서드를 자동 생성합니다.</li>
 *   <li>{@code @ToString} - 객체의 문자열 표현을 자동 생성합니다.</li>
 * </ul>
 */
@Entity
@Table(name = "user_role")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private Long userRoleId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "authority_id")
    private Short authorityId;
}