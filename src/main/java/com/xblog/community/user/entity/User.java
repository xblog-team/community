package com.xblog.community.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "with_drawed_at")
    private LocalDateTime withDrawedAt;

    @Column(name = "password_change_date")
    private LocalDateTime passwordChangeDate;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "withdraw")
    private Boolean withdraw;
}