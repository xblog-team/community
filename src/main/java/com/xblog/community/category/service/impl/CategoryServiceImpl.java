package com.xblog.community.category.service.impl;

import com.xblog.community.category.dto.AddCategoryRequest;
import com.xblog.community.category.dto.GetCategoryResponse;
import com.xblog.community.category.dto.ModifyCategoryDto;
import com.xblog.community.category.entity.Category;
import com.xblog.community.category.exception.CategoryNotFoundException;
import com.xblog.community.category.repository.CategoryRepository;
import com.xblog.community.category.service.CategoryService;
import com.xblog.community.party.entity.Party;
import com.xblog.community.party.exception.PartyNotFoundException;
import com.xblog.community.party.repository.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 카테고리 관련 service
 * @author jihyeon
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final PartyRepository partyRepository;

    /**
     * 카테고리를 추가하는 method
     * @param dto categoryName, partyId로 구성된 dto
     */
    public void createCategory(AddCategoryRequest dto) {
        Party party = partyRepository.findById(dto.getPartyId()).orElseThrow(() -> new PartyNotFoundException("해당 그룹을 찾을 수 없습니다."));
        Category category = Category.builder()
                .categoryName(dto.getCategoryName())
                .party(party)
                .build();
        categoryRepository.save(category);
    }

    /**
     * 특정 카테고리에 관한 내용을 조회하는 method
     * @param categoryId 카테고리 아이디
     * @return categoryId, categoryName, partyId로 구성된 dto
     */
    public GetCategoryResponse viewCategory(long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다."));
        return new GetCategoryResponse(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getParty().getPartyId()
        );
    }

    /**
     * 특정 그룹에 속한 모든 카테고리들을 최신순으로 조회한 리스트들을 dto로 바꾸는 method
     * @param partyId 그룹 아이디
     * @return categoryId, categoryName, partyId로 구성된 dto 리스트
     */
    public List<GetCategoryResponse> getCategoryList(long partyId) {
        List<Category> categories = categoryRepository.findByParty_PartyId(partyId);
        List<GetCategoryResponse> responses = new ArrayList<>();
        for (Category category : categories) {
            responses.add(new GetCategoryResponse(
                    category.getCategoryId(),
                    category.getCategoryName(),
                    category.getParty().getPartyId()
            ));
        }
        return responses;
    }

    /**
     * 카테고리를 수정하는 dto
     * @param dto categoryName, partyId로 구성된 dto
     * @param categoryId 카테고리 아이디
     * @return categoryName, partyId로 구성된 dto
     */
    public ModifyCategoryDto modifyCategory(ModifyCategoryDto dto, long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다."));
        Party party = partyRepository.findById(dto.getPartyId()).orElseThrow(() -> new PartyNotFoundException("해당 그룹을 찾을 수 없습니다."));

        Category modifyCategory = Category.builder()
                .categoryId(categoryId)
                .categoryName(dto.getCategoryName())
                .party(party)
                .build();

        Category newCategory = categoryRepository.save(modifyCategory);
        return new ModifyCategoryDto(
                newCategory.getCategoryName(),
                newCategory.getParty().getPartyId()
        );
    }

    /**
     * 카테고리를 삭제하는 method
     * @param categoryId 카테고리 아이디
     */
    public void deleteCategory(long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다."));
        categoryRepository.delete(category);
    }
}
