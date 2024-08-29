package com.xblog.community.user_party.service;

import java.util.List;

import com.xblog.community.user_party.dto.InviteRequestDto;
import com.xblog.community.user_party.entity.UserParty;

public interface UserPartyService {
    void inviteToParty(InviteRequestDto inviteRequestDto);
    void approveUser(String userId, Long partyId);
    void kickUser(String userId, Long partyId);
    List<UserParty> viewAllMembers(Long partyId);
    List<UserParty> viewManager(Long partyId);
    List<UserParty> viewMembers(Long partyId);
    List<UserParty> viewBanned(Long partyId);
}