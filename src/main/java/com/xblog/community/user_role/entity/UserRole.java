package com.xblog.community.user_role.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_role")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserRole {

    @Id
    @Column(name = "user_role_id")
    private Long userRoleId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "authority_id")
    private Short authorityId;
}
