package com.nttoan.handmadeshop.infrastructure.persistence.jpa.catalog.category.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nttoan.handmadeshop.domain.catalog.category.entity.Category;
import com.nttoan.handmadeshop.domain.catalog.category.repository.CategoryRepository;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.catalog.category.entity.CategoryJpaEntity;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.catalog.category.mapper.CategoryMapper;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.catalog.category.repository.JpaCategoryRepository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;

    public CategoryRepositoryImpl(JpaCategoryRepository jpaCategoryRepository) {
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    @Override
    public Category save(Category category) {
        CategoryJpaEntity saved = jpaCategoryRepository.save(CategoryMapper.toJpa(category));
        return CategoryMapper.toDomain(saved);
    }

    @Override
    public Optional<Category> findById(String id) {
        return jpaCategoryRepository.findById(id)
                .map(CategoryMapper::toDomain);
    }

    @Override
    public java.util.List<Category> findAll() {
        return jpaCategoryRepository.findAll().stream().map(CategoryMapper::toDomain)
                .collect(java.util.stream.Collectors.toList());
    }

}
