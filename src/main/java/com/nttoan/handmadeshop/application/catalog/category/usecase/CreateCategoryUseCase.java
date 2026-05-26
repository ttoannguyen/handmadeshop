package com.nttoan.handmadeshop.application.catalog.category.usecase;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.catalog.category.command.CreateCategoryCommand;
import com.nttoan.handmadeshop.application.catalog.category.dto.request.CreateCategoryRequest;
import com.nttoan.handmadeshop.application.catalog.category.dto.response.CreateCategoryResponse;
import com.nttoan.handmadeshop.application.catalog.category.mapper.CategoryAppMapper;
import com.nttoan.handmadeshop.application.common.dto.BaseResponse;
import com.nttoan.handmadeshop.domain.catalog.category.entity.Category;
import com.nttoan.handmadeshop.domain.catalog.category.repository.CategoryRepository;

@Service
public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public BaseResponse<CreateCategoryResponse> execute(CreateCategoryRequest request) {
        try {
            CreateCategoryCommand command = CategoryAppMapper.toCommand(request);

            Category category = new Category(command.name(), command.description());

            Category saved = categoryRepository.save(category);

            return BaseResponse.created(CategoryAppMapper.toResponse(saved));
        } catch (IllegalArgumentException e) {
            return BaseResponse.error("VALIDATION_ERROR", e.getMessage(), 400, null);
        } catch (Exception e) {
            return BaseResponse.error("ERROR", "Failed to create category", 500, e.getMessage());
        }
    }
}