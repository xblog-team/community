package com.xblog.community.user_role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xblog.community.user_role.entity.UserRole;
import java.util.List;
import java.util.Optional;

/**
 * UserRole 엔티티에 대한 데이터베이스 접근을 제공하는 JPA 리포지토리 인터페이스입니다.
 * 기본적인 CRUD 작업을 지원하며, 사용자 ID에 따른 사용자 역할 조회 기능을 포함합니다.
 * 
 * <p>주요 메서드:</p>
 * <ul>
 *   <li>{@link #findByUserId(String)} - 주어진 사용자 ID로 단일 사용자 역할을 조회합니다.</li>
 *   <li>{@link #getByUserId(String)} - 주어진 사용자 ID로 여러 사용자 역할을 조회합니다.</li>
 * </ul>
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
    Optional<UserRole> findByUserId(String userId);
    List<UserRole> getByUserId(String userId);
}