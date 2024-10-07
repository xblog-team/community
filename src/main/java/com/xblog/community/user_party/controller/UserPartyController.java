package com.xblog.community.user_party.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xblog.community.party.service.PartyService;
import com.xblog.community.user_party.dto.InviteRequestDto;
import com.xblog.community.user_party.entity.UserParty;
import com.xblog.community.user_party.service.UserPartyService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 사용자와 파티 관련 API 엔드포인트를 제공하는 컨트롤러 클래스입니다.
 * 이 클래스는 파티의 구성원 관리, 초대, 승인 및 파티 생성과 같은 기능을 포함합니다.
 * 
 * <p>주요 엔드포인트:</p>
 * <ul>
 *   <li>{@code GET /api/userparty/{partyId}} - 특정 파티의 모든 구성원을 조회합니다.</li>
 *   <li>{@code GET /api/userparty/{partyId}/managers} - 특정 파티의 관리자 목록을 조회합니다.</li>
 *   <li>{@code GET /api/userparty/{partyId}/members} - 특정 파티의 일반 구성원 목록을 조회합니다.</li>
 *   <li>{@code GET /api/userparty/{partyId}/banned} - 특정 파티에서 차단된 사용자를 조회합니다.</li>
 *   <li>{@code POST /api/userparty/invite} - 파티에 사용자를 초대합니다.</li>
 *   <li>{@code PUT /api/userparty/{partyId}/approve} - 파티에 사용자를 승인합니다.</li>
 *   <li>{@code PUT /api/userparty/{partyId}/kick} - 파티에서 사용자를 퇴출합니다.</li>
 *   <li>{@code POST /api/userparty/create} - 새로운 파티를 생성합니다.</li>
 *   <li>{@code PUT /api/userparty/{partyId}/name} - 파티의 이름을 변경합니다.</li>
 *   <li>{@code PUT /api/userparty/{partyId}/privacy} - 파티의 공개 여부를 변경합니다.</li>
 * </ul>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/userparty")
public class UserPartyController {

    private final UserPartyService userPartyService;
    private final PartyService partyService;

    @GetMapping("/{partyId}")
    public ResponseEntity<List<UserParty>> getAllMembers(@PathVariable("partyId") Long partyId) {
        return ResponseEntity.ok().body(userPartyService.viewAllMembers(partyId));
    }
    
    @GetMapping("/{partyId}/managers")
    public ResponseEntity<List<UserParty>> getManagers(@PathVariable("partyId") Long partyId) {
        return ResponseEntity.ok().body(userPartyService.viewManager(partyId));
    }
    
    @GetMapping("/{partyId}/members")
    public ResponseEntity<List<UserParty>> getMembers(@PathVariable("partyId") Long partyId) {
        return ResponseEntity.ok().body(userPartyService.viewMembers(partyId));
    }

    @GetMapping("/{partyId}/banned")
    public ResponseEntity<List<UserParty>> getBannedUsers(@PathVariable("partyId") Long partyId) {
        return ResponseEntity.ok().body(userPartyService.viewBanned(partyId));
    }

    @PostMapping("/invite")
    public ResponseEntity<Void> inviteToParty(@RequestBody InviteRequestDto inviteRequestDto) {
        userPartyService.inviteToParty(inviteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{partyId}/approve")
    public ResponseEntity<Void> approveUser(@PathVariable("partyId") Long partyId, @RequestParam String userId) {
        userPartyService.approveUser(userId, partyId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{partyId}/kick")
    public ResponseEntity<Void> kickUser(@PathVariable("partyId") Long partyId, @RequestParam String userId) {
        userPartyService.kickUser(userId, partyId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createParty(@RequestParam String partyName, @RequestParam Boolean isPrivate) {
        partyService.createParty(partyName, isPrivate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{partyId}/name")
    public ResponseEntity<Void> changePartyName(@PathVariable("partyId") Long partyId, @RequestParam String newPartyName) {
        partyService.changePartyName(partyId, newPartyName);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{partyId}/privacy")
    public ResponseEntity<Void> switchPartyPrivacy(@PathVariable("partyId") Long partyId, @RequestParam Boolean isPrivate) {
        partyService.switchTo(partyId, isPrivate);
        return ResponseEntity.ok().build();
    }
}