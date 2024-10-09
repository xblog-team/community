package com.xblog.community.party.repository;

import com.xblog.community.party.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Party 엔티티에 대한 데이터베이스 접근을 제공하는 JPA 리포지토리 인터페이스입니다.
 * 기본적인 CRUD 작업과 더불어 JPA에서 제공하는 다양한 데이터 접근 기능을 사용할 수 있습니다.
 */
public interface PartyRepository extends JpaRepository<Party, Long> {
}