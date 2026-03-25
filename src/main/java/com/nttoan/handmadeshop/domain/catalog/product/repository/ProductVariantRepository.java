package com.nttoan.handmadeshop.domain.catalog.product.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductVariantEntity;

public interface ProductVariantRepository extends JpaRepository<ProductVariantEntity, UUID> {
    boolean existsBySku(String sku);
}
