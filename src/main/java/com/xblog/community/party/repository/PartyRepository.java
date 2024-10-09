package com.xblog.community.party.repository;

import com.xblog.community.party.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 그룹 관련 repository
 * @author jihyeon
 */
public interface PartyRepository extends JpaRepository<Party, Long> {
}
