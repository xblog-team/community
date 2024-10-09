package com.xblog.community.user_party.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xblog.community.user_party.entity.UserParty;
import com.xblog.community.user_party.entity.UserParty.Role;

/**
 * UserParty 엔티티에 대한 데이터베이스 접근을 제공하는 JPA 리포지토리 인터페이스입니다.
 * 기본적인 CRUD 작업을 지원하며, 사용자와 파티 간의 관계를 관리하는 다양한 쿼리 메서드를 포함합니다.
 * 
 * <p>주요 메서드:</p>
 * <ul>
 *   <li>{@link #existsByUserIdAndPartyId(String, Long)} - 주어진 사용자 ID와 파티 ID로 사용자 파티 관계의 존재 여부를 확인합니다.</li>
 *   <li>{@link #findByUserIdAndPartyId(String, Long)} - 주어진 사용자 ID와 파티 ID로 사용자 파티 관계를 조회합니다.</li>
 *   <li>{@link #findByPartyIdAndIsAcceptedTrueAndIsRemovedFalse(Long)} - 특정 파티에서 수락된 사용자의 목록을 조회합니다.</li>
 *   <li>{@link #findByPartyIdAndIsRemovedFalseAndRole(Long, Role)} - 특정 파티에서 제거되지 않은 특정 역할의 사용자를 조회합니다.</li>
 *   <li>{@link #findByPartyIdAndIsRemovedTrue(Long)} - 특정 파티에서 제거된 사용자 목록을 조회합니다.</li>
 * </ul>
 */
public interface UserPartyRepository extends JpaRepository<UserParty, Long>{
    Boolean existsByUserIdAndPartyId(String userId, Long partyId);
    Optional<UserParty> findByUserIdAndPartyId(String userId, Long partyId);
    List<UserParty> findByPartyIdAndIsAcceptedTrueAndIsRemovedFalse(Long partyId);
    List<UserParty> findByPartyIdAndIsRemovedFalseAndRole(Long partyId, Role role);
    List<UserParty> findByPartyIdAndIsRemovedTrue(Long partyId);
}