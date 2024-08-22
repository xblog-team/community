package com.xblog.community.user_party.service;

import com.xblog.community.user_party.dto.InviteRequestDto;

public interface UserPartyService {
    void inviteToParty(InviteRequestDto inviteRequestDto);
    void approveUser(String userId, Long partyId);
    void kickUser(String userId, Long partyId);
    void viewAllMembers(Long partyId);
    void viewManager(Long partyId);
    void viewMembers(Long partyId);
    void viewBanned(Long partyId);
}