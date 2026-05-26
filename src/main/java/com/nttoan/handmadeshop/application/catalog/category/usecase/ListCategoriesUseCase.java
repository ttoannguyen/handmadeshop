package com.nttoan.handmadeshop.application.catalog.category.usecase;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.catalog.category.dto.response.CreateCategoryResponse;
import com.nttoan.handmadeshop.application.common.dto.BaseResponse;
import com.nttoan.handmadeshop.domain.catalog.category.repository.CategoryRepository;

@Service
public class ListCategoriesUseCase {

    private final CategoryRepository categoryRepository;

    public ListCategoriesUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public BaseResponse<java.util.List<CreateCategoryResponse>> execute() {
        try {
            java.util.List<CreateCategoryResponse> list = categoryRepository.findAll().stream()
                    .map(c -> CreateCategoryResponse.builder()
                            .name(c.getName())
                            .description(c.getDescription())
                            .active(c.isActive())
                            .build())
                    .collect(Collectors.toList());
            return BaseResponse.success(list);
        } catch (Exception e) {
            return BaseResponse.error("ERROR", "Failed to list categories", 500, e.getMessage());
        }
    }
}
