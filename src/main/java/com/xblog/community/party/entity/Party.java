package com.xblog.community.party.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "party")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Party {
    @Id
    @Column(name = "party_id")
    private long partyId;

    @Column(name = "party_name")
    private String partyName;

    @Column(name = "is_private")
    private int isPrivate;
}

