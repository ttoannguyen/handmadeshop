package com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nttoan.handmadeshop.domain.catalog.product.entity.Product;
import com.nttoan.handmadeshop.domain.catalog.product.repository.ProductRepository;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.entity.ProductJpaEntity;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.mapper.ProductMapper;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.repository.JpaProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaRepository;

    public ProductRepositoryImpl(JpaProductRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Product save(Product product) {
        ProductJpaEntity entity = ProductMapper.toJpa(product);
        ProductJpaEntity saved = jpaRepository.save(entity);
        return product; // (tạm thời)
    }

    // @Override
    // public Product save(Product product) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'save'");
    // }

    @Override
    public Optional<Product> findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}