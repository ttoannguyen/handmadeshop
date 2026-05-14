package com.nttoan.handmadeshop.application.catalog.category.usecase;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.catalog.category.dto.request.CreateCategoryRequest;
import com.nttoan.handmadeshop.domain.catalog.category.entity.Category;
import com.nttoan.handmadeshop.domain.catalog.category.repository.CategoryRepository;

@Service
public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category execute(CreateCategoryRequest request) {

        Category category = new Category(
                request.getName(),
                request.getDescription());

        return categoryRepository.save(category);
    }
}