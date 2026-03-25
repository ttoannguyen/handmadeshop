package com.nttoan.handmadeshop.domain.catalog.category.service.impl;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.domain.catalog.category.dto.request.CategoryRequest;
import com.nttoan.handmadeshop.domain.catalog.category.dto.response.CategoryResponse;
import com.nttoan.handmadeshop.domain.catalog.category.entity.CategoryEntity;
import com.nttoan.handmadeshop.domain.catalog.category.mapper.CategoryMapper;
import com.nttoan.handmadeshop.domain.catalog.category.repository.CategoryRepository;
import com.nttoan.handmadeshop.domain.catalog.category.service.CategoryService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryResponse create(CategoryRequest request) {
        String slug = slugify(request.getName());
        System.out.println("slug: " + slug);

        if (categoryRepository.existsBySlug(slug))
            throw new RuntimeException("Slug already exists");

        CategoryEntity parent = null;
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent not found"));

            // if (parent.getId().equals(request.getParentId()))
            // throw new RuntimeException("Invalid Request: Can not set parent id equals
            // id");
        }
        CategoryEntity category = CategoryEntity.builder()
                .name(request.getName())
                .slug(slug)
                .parentCategory(parent).build();

        CategoryEntity saved = categoryRepository.save(category);
        return categoryMapper.toCategoryResponse(saved);
    }

    private String slugify(String input) {
        return input.toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("\\s+", "-");
    }
}
