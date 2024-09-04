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
