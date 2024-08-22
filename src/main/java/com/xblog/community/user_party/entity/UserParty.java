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

@Entity
@Table(name = "user_party")
@Getter
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
