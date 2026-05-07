package com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.entity.ProductJpaEntity;

public interface JpaProductRepository extends JpaRepository<ProductJpaEntity, String> {

}
