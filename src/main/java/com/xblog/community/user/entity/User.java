package com.xblog.community.user.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 사용자 정보를 나타내는 엔티티 클래스입니다.
 * 이 클래스는 사용자의 ID, 비밀번호, 닉네임, 생성 시간, 탈퇴 시간, 비밀번호 변경 날짜 및 활성화 상태를 포함합니다.
 * 
 * <p>주요 필드:</p>
 * <ul>
 *   <li>{@code userId} - 사용자의 고유 식별자입니다.</li>
 *   <li>{@code password} - 사용자의 비밀번호입니다.</li>
 *   <li>{@code nickname} - 사용자의 닉네임입니다.</li>
 *   <li>{@code createdAt} - 사용자가 생성된 날짜 및 시간입니다.</li>
 *   <li>{@code withdrawedAt} - 사용자가 탈퇴한 날짜 및 시간입니다. 사용자가 탈퇴하지 않은 경우 null입니다.</li>
 *   <li>{@code passwordChangeDate} - 사용자가 비밀번호를 마지막으로 변경한 날짜 및 시간입니다.</li>
 *   <li>{@code enabled} - 사용자의 계정 활성화 상태를 나타내는 플래그입니다. 기본값은 true입니다.</li>
 *   <li>{@code withdraw} - 사용자의 탈퇴 상태를 나타내는 플래그입니다. 기본값은 false입니다.</li>
 * </ul>
 * 
 * <p>주요 메서드:</p>
 * <ul>
 *   <li>{@link #onCreate()} - 사용자가 생성될 때 호출되어 생성 시간과 비밀번호 변경 날짜를 초기화합니다.</li>
 * </ul>
 */
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

    private Boolean enabled = true;

    private Boolean withdraw = false;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();

        if (this.passwordChangeDate == null) {
            this.passwordChangeDate = LocalDateTime.now();
        }
    }

}