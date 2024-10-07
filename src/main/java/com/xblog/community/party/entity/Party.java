package com.xblog.community.party.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * 시스템 내의 파티(모임)를 나타내는 엔티티입니다.
 * 각 파티는 고유 식별자와 이름, 그리고 공개 여부를 가집니다.
 * 데이터베이스의 `party` 테이블과 매핑됩니다.
 * 
 * <p>필드 설명:</p>
 * <ul>
 *   <li><b>partyId</b>: 파티의 고유 식별자 (자동 생성).</li>
 *   <li><b>partyName</b>: 파티의 이름.</li>
 *   <li><b>isPrivate</b>: 파티의 공개 여부 (true: 비공개, false: 공개).</li>
 * </ul>
 * 
 * <p>기본 생성자는 {@code protected}로 설정되어 있으며, 
 * 객체 생성을 위해서는 {@code @Builder}를 사용해야 합니다.</p>
 */
@Entity
@Table(name = "party")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private Long partyId;

    @Column(name = "party_name")
    private String partyName;

    @Column(name = "is_private")
    private Boolean isPrivate;

    @Builder
    public Party(String partyName, Boolean isPrivate) {
        this.partyName = partyName;
        this.isPrivate = isPrivate;
    }
}