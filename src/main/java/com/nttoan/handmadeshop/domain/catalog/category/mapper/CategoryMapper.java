package com.nttoan.handmadeshop.domain.catalog.category.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nttoan.handmadeshop.domain.catalog.category.dto.response.CategoryResponse;
import com.nttoan.handmadeshop.domain.catalog.category.entity.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "parentId", source = "parentCategory.id")
    @Mapping(target = "parentName", source = "parentCategory.name")
    CategoryResponse toCategoryResponse(CategoryEntity entity);
}
