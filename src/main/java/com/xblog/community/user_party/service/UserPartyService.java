package com.xblog.community.user_party.service;

import java.util.List;

import com.xblog.community.user_party.dto.InviteRequestDto;
import com.xblog.community.user_party.entity.UserParty;

/**
 * 사용자와 파티 간의 관계를 관리하는 서비스 인터페이스입니다.
 * 이 인터페이스는 사용자 초대, 승인, 퇴출 및 파티 구성원 조회와 관련된 메서드를 정의합니다.
 * 
 * <p>주요 메서드:</p>
 * <ul>
 *   <li>{@link #inviteToParty(InviteRequestDto)} - 주어진 초대 요청 DTO를 사용하여 파티에 사용자를 초대합니다.</li>
 *   <li>{@link #approveUser(String, Long)} - 특정 파티에 대해 주어진 사용자 ID의 초대를 승인합니다.</li>
 *   <li>{@link #kickUser(String, Long)} - 특정 파티에서 주어진 사용자 ID를 퇴출합니다.</li>
 *   <li>{@link #viewAllMembers(Long)} - 특정 파티의 모든 구성원 목록을 조회합니다.</li>
 *   <li>{@link #viewManager(Long)} - 특정 파티의 관리자 목록을 조회합니다.</li>
 *   <li>{@link #viewMembers(Long)} - 특정 파티의 일반 구성원 목록을 조회합니다.</li>
 *   <li>{@link #viewBanned(Long)} - 특정 파티에서 차단된 사용자 목록을 조회합니다.</li>
 * </ul>
 */
public interface UserPartyService {
    void inviteToParty(InviteRequestDto inviteRequestDto);
    void approveUser(String userId, Long partyId);
    void kickUser(String userId, Long partyId);
    List<UserParty> viewAllMembers(Long partyId);
    List<UserParty> viewManager(Long partyId);
    List<UserParty> viewMembers(Long partyId);
    List<UserParty> viewBanned(Long partyId);
}