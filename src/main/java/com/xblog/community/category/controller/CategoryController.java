package com.xblog.community.category.controller;

import com.xblog.community.category.dto.AddCategoryRequest;
import com.xblog.community.category.dto.GetCategoryResponse;
import com.xblog.community.category.dto.ModifyCategoryDto;
import com.xblog.community.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 카테고리 관련 controller
 * @author jihyeon
 */
@RequestMapping("/api/categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * 카테고리를 추가하는 method
     * @param dto categoryName, partyId로 구성된 dto
     * @return 201 Created
     */
    @PostMapping
    public ResponseEntity<Void> addCategory(@RequestBody AddCategoryRequest dto) {
        categoryService.createCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 특정 카테고리에 대한 정보를 조회하는 method
     * @param categoryId 카테고리 아이디
     * @return categoryId, categoryName, partyId로 구성된 dto, 200 OK
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<GetCategoryResponse> getCategory(@PathVariable long categoryId) {
        GetCategoryResponse body = categoryService.viewCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    /**
     * 특정 그룹에 있는 모든 카테고리를 조회하는 method
     * @param partyId 그룹 아이디
     * @return categoryId, categoryName, partyId로 구성된 dto 리스트, 200 OK
     */
    @GetMapping("/category-parties/{partyId}")
    public ResponseEntity<List<GetCategoryResponse>> getCategoryByPartyId(@PathVariable long partyId) {
        List<GetCategoryResponse> list = categoryService.getCategoryList(partyId);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    /**
     * 카테고리를 수정하는 method
     * @param dto categoryName, partyId로 구성된 dto
     * @param categoryId 카테고리 아이디
     * @return categoryName, partyId로 구성된 dto, 200 OK
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<ModifyCategoryDto> putCategory(@RequestBody ModifyCategoryDto dto, @PathVariable long categoryId) {
        ModifyCategoryDto body = categoryService.modifyCategory(dto, categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    /**
     * 카테고리를 삭제하는 method
     * @param categoryId 카테고리아이디
     * @return 200 OK
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

