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

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<Void> addCategory(@RequestBody AddCategoryRequest dto) {
        categoryService.createCategory(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<GetCategoryResponse> getCategory(@PathVariable long categoryId) {
        GetCategoryResponse body = categoryService.viewCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @GetMapping("/categories/{partyId}")
    public ResponseEntity<List<GetCategoryResponse>> getCategoryByPartyId(@PathVariable long partyId) {
        List<GetCategoryResponse> list = categoryService.getCategoryList(partyId);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<ModifyCategoryDto> putCategory(@RequestBody ModifyCategoryDto dto, @PathVariable long categoryId) {
        ModifyCategoryDto body = categoryService.modifyCategory(dto, categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

