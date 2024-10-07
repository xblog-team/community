package com.xblog.community.authority.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 시스템 내의 권한 또는 역할을 나타냅니다.
 * 데이터베이스의 `authority` 테이블과 매핑됩니다.
 */
@Entity
@Table(name = "authority")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Authority {

    @Id
    @Column(name = "authority_id")
    private Short authorityId;

    private String authority;
}
