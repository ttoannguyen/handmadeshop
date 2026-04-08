package com.nttoan.handmadeshop.domain.catalog.product.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    List<ProductEntity> findByActive(boolean active);
}
