package com.nttoan.handmadeshop.domain.catalog.category.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.domain.catalog.category.dto.request.CategoryRequest;
import com.nttoan.handmadeshop.domain.catalog.category.dto.response.CategoryResponse;
import com.nttoan.handmadeshop.domain.catalog.category.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping()
    public CategoryResponse createCategory(@RequestBody CategoryRequest entity) {
        return categoryService.create(entity);
    }

    @GetMapping()
    public List<CategoryResponse> getCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/tree")
    public List<CategoryResponse> getTree() {
        return categoryService.getTree();
    }

}
