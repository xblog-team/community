package com.xblog.community.user_party.dto;

import com.xblog.community.user_party.entity.UserParty.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InviteRequestDto {
    private String userId;
    private Long partyId;
    private Role role;
}
