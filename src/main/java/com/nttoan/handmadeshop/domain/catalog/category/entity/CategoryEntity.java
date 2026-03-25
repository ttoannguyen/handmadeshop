package com.nttoan.handmadeshop.domain.catalog.category.entity;

import java.util.ArrayList;
import java.util.List;

import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductCategoryEntity;
import com.nttoan.handmadeshop.domain.common.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CategoryEntity parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    @Builder.Default
    private List<CategoryEntity> children = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    @Builder.Default
    private List<ProductCategoryEntity> productCategories = new ArrayList<>();
}