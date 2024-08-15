package com.xblog.community.user_role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xblog.community.user_role.dto.UserRoleOnlyResponseDto;
import com.xblog.community.user_role.entity.UserRole;
import java.util.List;


public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
    List<UserRoleOnlyResponseDto> findByUserId(String userId);
}
