package com.xblog.community.category.service;

import com.xblog.community.category.dto.AddCategoryRequest;
import com.xblog.community.category.dto.GetCategoryResponse;
import com.xblog.community.category.dto.ModifyCategoryDto;

import java.util.List;

public interface CategoryService {
    void createCategory(AddCategoryRequest dto);
    GetCategoryResponse viewCategory(long categoryId);
    List<GetCategoryResponse> getCategoryList(long partyId);
    ModifyCategoryDto modifyCategory(ModifyCategoryDto dto, long categoryId);
    void deleteCategory(long categoryId);
}
