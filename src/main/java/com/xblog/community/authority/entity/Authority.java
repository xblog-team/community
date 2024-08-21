package com.xblog.community.authority.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
