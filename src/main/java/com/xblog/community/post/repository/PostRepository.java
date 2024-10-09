package com.xblog.community.post.repository;

import com.xblog.community.party.entity.Party;
import com.xblog.community.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 게시물 관련 repository
 * @author jihyeon
 */
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategory_CategoryId(Long categoryId);

    List<Post> findByCategory_PartyOrderByPostIdDesc(Party party);

    List<Post> findByCategory_PartyOrderByViewsDesc(Party party);

    List<Post> findByCategory_CategoryIdOrderByViewsDesc(Long categoryId);
}
