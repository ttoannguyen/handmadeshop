package com.nttoan.handmadeshop.infrastructure.persistence.jpa.catalog.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttoan.handmadeshop.infrastructure.persistence.jpa.catalog.product.entity.ProductJpaEntity;

public interface JpaProductRepository extends JpaRepository<ProductJpaEntity, String> {

}
