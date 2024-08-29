package com.xblog.community.party.service;

public interface PartyService {
    void createParty(String partyName, Boolean isPrivate);
    void changePartyName(Long partyId, String partyName);
    void switchTo(Long partyId, Boolean isPrivate);
}
