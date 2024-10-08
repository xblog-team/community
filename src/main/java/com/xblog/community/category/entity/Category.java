package com.xblog.community.category.entity;

import com.xblog.community.party.entity.Party;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "category")
@NoArgsConstructor
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;

    @Builder
    public Category(Long categoryId, String categoryName, Party party) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.party = party;
    }
}
