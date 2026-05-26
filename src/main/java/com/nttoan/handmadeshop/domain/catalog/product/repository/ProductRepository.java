package com.nttoan.handmadeshop.domain.catalog.product.repository;

import java.util.Optional;
import java.util.List;

import com.nttoan.handmadeshop.domain.catalog.product.entity.Product;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(String id);

    List<Product> findAll();
}
