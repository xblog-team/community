package com.xblog.community.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyCategoryDto {
    public String categoryName;
    public Long partyId;
}
