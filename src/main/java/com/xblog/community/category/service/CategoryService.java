package com.xblog.community.category.service;

import com.xblog.community.category.dto.AddCategoryRequest;
import com.xblog.community.category.dto.GetCategoryResponse;
import com.xblog.community.category.dto.ModifyCategoryDto;
import com.xblog.community.category.entity.Category;
import com.xblog.community.category.exception.CategoryNotFoundException;
import com.xblog.community.category.repository.CategoryRepository;
import com.xblog.community.party.entity.Party;
import com.xblog.community.party.exception.PartyNotFoundException;
import com.xblog.community.party.repository.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final PartyRepository partyRepository;

    public void createCategory(AddCategoryRequest dto) {
        Party party = partyRepository.findById(dto.getPartyId()).orElseThrow(() -> new PartyNotFoundException("해당 그룹을 찾을 수 없습니다."));
        Category category = Category.builder()
                .categoryName(dto.getCategoryName())
                .party(party)
                .build();
        categoryRepository.save(category);
    }

    public GetCategoryResponse viewCaegory(long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다."));
        return new GetCategoryResponse(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getParty().getPartyId()
        );
    }

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

    public ModifyCategoryDto modifyCategory(ModifyCategoryDto dto, long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다."));
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

    public void deleteCategory(long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다."));
        categoryRepository.delete(category);
    }
}
