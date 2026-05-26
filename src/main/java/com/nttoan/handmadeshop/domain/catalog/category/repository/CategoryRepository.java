package com.nttoan.handmadeshop.domain.catalog.category.repository;

import java.util.Optional;
import java.util.List;

import com.nttoan.handmadeshop.domain.catalog.category.entity.Category;

public interface CategoryRepository {
    Category save(Category category);

    Optional<Category> findById(String id);

    List<Category> findAll();
}