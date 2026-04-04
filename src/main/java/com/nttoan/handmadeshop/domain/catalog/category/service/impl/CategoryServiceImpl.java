package com.nttoan.handmadeshop.domain.catalog.category.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepository.findAll()
                .stream().map(
                        entity -> {
                            CategoryResponse dto = categoryMapper.toCategoryResponse(entity);
                            dto.setChildren(null);
                            return dto;
                        })
                .toList();
    }

    @Override
    public List<CategoryResponse> getTree() {

        List<CategoryEntity> all = categoryRepository.findAll();
        Map<UUID, CategoryResponse> map = new HashMap<>();

        for (CategoryEntity entity : all) {
            CategoryResponse dto = categoryMapper.toCategoryResponse(entity);
            dto.setChildren(new ArrayList<>());
            map.put(entity.getId(), dto);
        }

        List<CategoryResponse> roots = new ArrayList<>();

        for (CategoryEntity entity : all) {
            CategoryResponse dto = map.get(entity.getId());

            if (entity.getParentCategory() == null) {
                roots.add(dto);
            } else {
                UUID parentId = entity.getParentCategory().getId();
                CategoryResponse parent = map.get(parentId);
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(dto);
            }
        }
        return roots;
    }
}
