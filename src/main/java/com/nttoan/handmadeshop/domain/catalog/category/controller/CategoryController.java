package com.nttoan.handmadeshop.domain.catalog.category.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.domain.catalog.category.dto.request.CategoryRequest;
import com.nttoan.handmadeshop.domain.catalog.category.dto.response.CategoryResponse;
import com.nttoan.handmadeshop.domain.catalog.category.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping()
    public CategoryResponse createCategory(@RequestBody CategoryRequest entity) {

        return categoryService.create(entity);
    }

}
