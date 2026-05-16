package com.nttoan.handmadeshop.application.catalog.category.usecase;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.catalog.category.command.CreateCategoryCommand;
import com.nttoan.handmadeshop.application.catalog.category.dto.request.CreateCategoryRequest;
import com.nttoan.handmadeshop.application.catalog.category.dto.response.CreateCategoryResponse;
import com.nttoan.handmadeshop.application.catalog.category.mapper.CategoryAppMapper;
import com.nttoan.handmadeshop.domain.catalog.category.entity.Category;
import com.nttoan.handmadeshop.domain.catalog.category.repository.CategoryRepository;

@Service
public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CreateCategoryResponse execute(CreateCategoryRequest request) {

        CreateCategoryCommand command = CategoryAppMapper.toCommand(request);

        Category category = new Category(command.name(), command.description());

        Category saved = categoryRepository.save(category);

        return CategoryAppMapper.toResponse(saved);
    }
}