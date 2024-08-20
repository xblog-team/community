package com.xblog.community.category.repository;

import com.xblog.community.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByParty_PartyId(Long partyId);
}
