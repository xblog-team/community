package com.xblog.community.category.repository;

import com.xblog.community.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 카테고리 관련 repository
 * @author jihyeon
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByParty_PartyId(Long partyId);
}
