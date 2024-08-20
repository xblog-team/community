package com.xblog.community.post.repository;

import com.xblog.community.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReposiotry extends JpaRepository<Post, Long> {
}
