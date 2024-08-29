package com.xblog.community.user_party.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xblog.community.user_party.dto.InviteRequestDto;
import com.xblog.community.user_party.entity.UserParty;
import com.xblog.community.user_party.entity.UserParty.Role;
import com.xblog.community.user_party.exception.AlreadyInPartyException;
import com.xblog.community.user_party.exception.UserIsNotInPartyException;
import com.xblog.community.user_party.repository.UserPartyRepository;
import com.xblog.community.user_party.service.UserPartyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPartyServiceImpl implements UserPartyService{
    private final UserPartyRepository userPartyRepository;

    @Override
    public void inviteToParty(InviteRequestDto inviteRequestDto) {
        if(userPartyRepository.existsByUserIdAndPartyId(inviteRequestDto.getUserId(), inviteRequestDto.getPartyId())){
            throw new AlreadyInPartyException();
        }

        UserParty userParty = UserParty.builder()
            .userId(inviteRequestDto.getUserId())
            .partyId(inviteRequestDto.getPartyId())
            .role(inviteRequestDto.getRole())
            .build();

        userPartyRepository.save(userParty);
    }

    @Override
    public void approveUser(String userId, Long partyId) {
        UserParty userParty = userPartyRepository.findByUserIdAndPartyId(userId, partyId).orElseThrow(() -> new UserIsNotInPartyException());
        userParty.setIsAccepted(true);

        userPartyRepository.save(userParty);
    }

    @Override
    public void kickUser(String userId, Long partyId) {
        UserParty userParty = userPartyRepository.findByUserIdAndPartyId(userId, partyId).orElseThrow(() -> new UserIsNotInPartyException());
        userParty.setIsRemoved(true);

        userPartyRepository.save(userParty);
    }

    @Override
    public List<UserParty> viewAllMembers(Long partyId) {
        return userPartyRepository.findByPartyIdAndIsAcceptedTrueAndIsRemovedFalse(partyId);
    }

    @Override
    public List<UserParty> viewManager(Long partyId) {
        return userPartyRepository.findByPartyIdAndIsRemovedFalseAndRole(partyId, Role.MANAGER);
    }

    @Override
    public List<UserParty> viewMembers(Long partyId) {
        return userPartyRepository.findByPartyIdAndIsRemovedFalseAndRole(partyId, Role.MEMBER);
    }

    @Override
    public List<UserParty> viewBanned(Long partyId) {
        return userPartyRepository.findByPartyIdAndIsRemovedTrue(partyId);
    }

}
