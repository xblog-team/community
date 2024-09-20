package com.xblog.community.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetCategoryResponse {
    private Long categoryId;
    private String categoryName;
    private Long partyId;
}
