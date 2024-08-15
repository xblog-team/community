package com.xblog.community.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    private String password;

    private String nickname;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "withdrawed_at")
    private LocalDateTime withdrawedAt;

    @Column(name = "password_change_date")
    private LocalDateTime passwordChangeDate;

    private Boolean enabled;

    private Boolean withdraw;
}
