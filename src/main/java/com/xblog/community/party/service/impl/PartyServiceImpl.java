package com.xblog.community.party.service.impl;

import org.springframework.stereotype.Service;

import com.xblog.community.party.entity.Party;
import com.xblog.community.party.exception.PartyNotFoundException;
import com.xblog.community.party.repository.PartyRepository;
import com.xblog.community.party.service.PartyService;

import lombok.RequiredArgsConstructor;

/**
 * PartyService 인터페이스의 구현체로, 파티(모임) 관련 비즈니스 로직을 처리합니다.
 * 파티의 생성, 이름 변경, 공개/비공개 전환 등의 기능을 제공합니다.
 * 
 * <p>주요 메서드:</p>
 * <ul>
 *   <li>{@link #createParty(String, Boolean)} - 새로운 파티를 생성하여 데이터베이스에 저장.</li>
 *   <li>{@link #changePartyName(Long, String)} - 특정 파티의 이름을 변경.</li>
 *   <li>{@link #switchTo(Long, Boolean)} - 파티의 공개 여부를 변경.</li>
 * </ul>
 */
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