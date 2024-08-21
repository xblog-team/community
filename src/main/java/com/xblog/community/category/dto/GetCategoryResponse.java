package com.xblog.community.category.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetCategoryResponse {
    public Long categoryId;
    public String categoryName;
    public Long partyId;
}
