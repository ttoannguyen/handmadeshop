package com.nttoan.handmadeshop.domain.catalog.product.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttoan.handmadeshop.domain.catalog.product.entity.ColorEntity;

public interface ColorRepository extends JpaRepository<ColorEntity, UUID> {
    boolean existsByName(String name);
}
