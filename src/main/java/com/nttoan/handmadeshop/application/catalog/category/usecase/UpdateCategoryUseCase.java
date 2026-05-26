package com.nttoan.handmadeshop.application.catalog.category.usecase;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.catalog.category.dto.request.UpdateCategoryRequest;
import com.nttoan.handmadeshop.application.catalog.category.mapper.CategoryAppMapper;
import com.nttoan.handmadeshop.application.catalog.category.dto.response.CreateCategoryResponse;
import com.nttoan.handmadeshop.application.common.dto.BaseResponse;
import com.nttoan.handmadeshop.domain.catalog.category.entity.Category;
import com.nttoan.handmadeshop.domain.catalog.category.repository.CategoryRepository;

@Service
public class UpdateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public UpdateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public BaseResponse<CreateCategoryResponse> execute(String id, UpdateCategoryRequest request) {
        try {
            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Category not found"));

            category.updateInfo(request.getName(), request.getDescription());

            Category saved = categoryRepository.save(category);
            return BaseResponse.success(CategoryAppMapper.toResponse(saved));
        } catch (NoSuchElementException e) {
            return BaseResponse.notFound(e.getMessage());
        } catch (IllegalArgumentException e) {
            return BaseResponse.error("VALIDATION_ERROR", e.getMessage(), 400, null);
        } catch (Exception e) {
            return BaseResponse.error("ERROR", "Failed to update category", 500, e.getMessage());
        }
    }
}
