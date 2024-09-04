package com.xblog.community.party.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    public Party(String partyName, Boolean isPrivate){
        this.partyName = partyName;
        this.isPrivate = isPrivate;
    }
}

