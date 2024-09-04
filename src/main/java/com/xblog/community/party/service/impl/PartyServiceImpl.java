package com.xblog.community.party.service.impl;

import org.springframework.stereotype.Service;

import com.xblog.community.party.entity.Party;
import com.xblog.community.party.exception.PartyNotFoundException;
import com.xblog.community.party.repository.PartyRepository;
import com.xblog.community.party.service.PartyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartyServiceImpl implements PartyService {
    private final PartyRepository partyRepository;
    
    @Override
    public void createParty(String partyName, Boolean isPrivate) {
        Party newParty = Party.builder()
            .partyName(partyName)
            .isPrivate(isPrivate)
            .build();

        partyRepository.save(newParty);
    }

    @Override
    public void changePartyName(Long partyId, String partyName) {
        Party party = partyRepository.findById(partyId)
            .orElseThrow(() -> new PartyNotFoundException("Party not found with id: " + partyId));
        
        party.setPartyName(partyName);
        partyRepository.save(party);
    }

    @Override
    public void switchTo(Long partyId, Boolean isPrivate) {
        Party party = partyRepository.findById(partyId)
            .orElseThrow(() -> new PartyNotFoundException("Party not found with id: " + partyId));
        
        party.setIsPrivate(isPrivate);
        partyRepository.save(party);
    }

}
