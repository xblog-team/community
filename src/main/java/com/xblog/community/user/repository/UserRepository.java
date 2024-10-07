package com.xblog.community.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xblog.community.user.entity.User;

/**
 * 사용자 엔티티에 대한 데이터 액세스 레이어 인터페이스입니다.
 * 이 인터페이스는 JpaRepository를 상속받아 사용자 정보를 관리하는 메서드를 제공합니다.
 * 
 * @param User 사용자 엔티티
 * @param String 사용자 ID의 데이터 타입
 */
public interface UserRepository extends JpaRepository<User, String>{   
}