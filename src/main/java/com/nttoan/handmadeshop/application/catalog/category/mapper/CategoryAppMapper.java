package com.nttoan.handmadeshop.application.catalog.category.mapper;

import com.nttoan.handmadeshop.application.catalog.category.command.CreateCategoryCommand;
import com.nttoan.handmadeshop.application.catalog.category.dto.request.CreateCategoryRequest;
import com.nttoan.handmadeshop.application.catalog.category.dto.response.CreateCategoryResponse;
import com.nttoan.handmadeshop.domain.catalog.category.entity.Category;

public class CategoryAppMapper {

    public static CreateCategoryCommand toCommand(CreateCategoryRequest req) {
        return new CreateCategoryCommand(
                req.getName(),
                req.getDescription());
    }

    public static CreateCategoryResponse toResponse(Category category) {
        return CreateCategoryResponse.builder()
                .name(category.getName())
                .description(category.getDescription())
                .active(category.isActive())
                .build();
    }
}