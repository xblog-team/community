package com.xblog.community.user_party.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xblog.community.user_party.entity.UserParty;
import com.xblog.community.user_party.entity.UserParty.Role;

public interface UserPartyRepository extends JpaRepository<UserParty, Long>{
    Boolean existsByUserIdAndPartyId(String userId, Long partyId);
    Optional<UserParty> findByUserIdAndPartyId(String userId, Long partyId);
    List<UserParty> findByPartyIdAndIsAcceptedTrueAndIsRemovedFalse(Long partyId);
    List<UserParty> findByPartyIdAndIsRemovedFalseAndRole(Long partyId, Role role);
    List<UserParty> findByPartyIdAndIsRemovedTrue(Long partyId);
}