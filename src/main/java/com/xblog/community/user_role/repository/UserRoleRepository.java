package com.xblog.community.user_role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xblog.community.user_role.entity.UserRole;
import java.util.List;
import java.util.Optional;


public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
    Optional<UserRole> findByUserId(String userId);
    List<UserRole> getByUserId(String userId);
}