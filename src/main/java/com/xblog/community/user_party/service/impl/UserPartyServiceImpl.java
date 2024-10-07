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

/**
 * 사용자와 파티 간의 관계를 관리하는 서비스 구현 클래스입니다.
 * 이 클래스는 사용자의 초대, 승인, 퇴출 및 파티 구성원 조회와 관련된 기능을 제공합니다.
 * 
 * <p>주요 기능:</p>
 * <ul>
 *   <li>{@link #inviteToParty(InviteRequestDto)} - 주어진 초대 요청 DTO를 사용하여 사용자를 파티에 초대합니다. 사용자가 이미 파티에 속해 있는 경우 예외를 발생시킵니다.</li>
 *   <li>{@link #approveUser(String, Long)} - 특정 파티에 대한 사용자의 초대를 승인합니다.</li>
 *   <li>{@link #kickUser(String, Long)} - 특정 파티에서 사용자를 퇴출합니다.</li>
 *   <li>{@link #viewAllMembers(Long)} - 특정 파티의 모든 수락된 구성원 목록을 조회합니다.</li>
 *   <li>{@link #viewManager(Long)} - 특정 파티의 관리자 목록을 조회합니다.</li>
 *   <li>{@link #viewMembers(Long)} - 특정 파티의 일반 구성원 목록을 조회합니다.</li>
 *   <li>{@link #viewBanned(Long)} - 특정 파티에서 차단된 사용자 목록을 조회합니다.</li>
 * </ul>
 */
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