package com.nttoan.handmadeshop.infrastructure.persistence.jpa.catalog.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttoan.handmadeshop.infrastructure.persistence.jpa.catalog.category.entity.CategoryJpaEntity;

public interface JpaCategoryRepository extends JpaRepository<CategoryJpaEntity, String> {

}
