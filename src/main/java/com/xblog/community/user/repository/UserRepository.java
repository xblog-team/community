package com.xblog.community.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xblog.community.user.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
    
}
