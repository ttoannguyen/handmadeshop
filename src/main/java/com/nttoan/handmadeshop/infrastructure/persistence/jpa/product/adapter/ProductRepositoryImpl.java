package com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nttoan.handmadeshop.domain.catalog.product.entity.Product;
import com.nttoan.handmadeshop.domain.catalog.product.repository.ProductRepository;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.category.entity.CategoryJpaEntity;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.category.repository.JpaCategoryRepository;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.entity.ProductJpaEntity;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.mapper.ProductMapper;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.repository.JpaProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository productRepository;

    private final JpaCategoryRepository categoryRepository;

    public ProductRepositoryImpl(JpaProductRepository productRepository, JpaCategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product save(Product product) {
        CategoryJpaEntity category = categoryRepository.findById(product.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        ProductJpaEntity saved = productRepository.save(ProductMapper.toJpa(product, category));
        return ProductMapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(String id) {
        return productRepository.findById(id)
                .map(ProductMapper::toDomain);
    }
}