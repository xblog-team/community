package com.xblog.community.post.repository;

import com.xblog.community.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostReposiotry extends JpaRepository<Post, Long> {
    List<Post> findByCategory_CategoryId(Long categoryId);
}
