package com.nttoan.handmadeshop.application.catalog.category.usecase;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.catalog.category.mapper.CategoryAppMapper;
import com.nttoan.handmadeshop.application.catalog.category.dto.response.CreateCategoryResponse;
import com.nttoan.handmadeshop.application.common.dto.BaseResponse;
import com.nttoan.handmadeshop.domain.catalog.category.repository.CategoryRepository;

@Service
public class GetCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public GetCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public BaseResponse<CreateCategoryResponse> execute(String id) {
        try {
            return categoryRepository.findById(id)
                    .map(c -> BaseResponse.success(CategoryAppMapper.toResponse(c)))
                    .orElseGet(() -> BaseResponse.notFound("Category not found"));
        } catch (Exception e) {
            return BaseResponse.error("ERROR", "Failed to get category", 500, e.getMessage());
        }
    }
}
