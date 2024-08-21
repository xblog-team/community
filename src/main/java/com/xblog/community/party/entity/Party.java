package com.xblog.community.party.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "party")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Party {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private long partyId;

    @Column(name = "party_name")
    private String partyName;

    @Column(name = "is_private")
    private int isPrivate;
}

