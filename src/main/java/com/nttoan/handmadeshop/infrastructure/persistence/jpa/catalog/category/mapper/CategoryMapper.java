package com.nttoan.handmadeshop.infrastructure.persistence.jpa.catalog.category.mapper;

import com.nttoan.handmadeshop.domain.catalog.category.entity.Category;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.catalog.category.entity.CategoryJpaEntity;

public class CategoryMapper {
    public static CategoryJpaEntity toJpa(Category category) {
        if (category == null)
            return null;

        CategoryJpaEntity entity = new CategoryJpaEntity();

        entity.setId(category.getId());
        entity.setName(category.getName());

        return entity;
    }

    public static Category toDomain(CategoryJpaEntity entity) {
        if (entity == null)
            return null;

        return new Category(
                entity.getId(),
                entity.getName());
    }
}
