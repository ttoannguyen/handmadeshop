package com.nttoan.handmadeshop.domain.catalog.category.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttoan.handmadeshop.domain.catalog.category.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    boolean existsBySlug(String slug);
}