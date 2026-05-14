package com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.nttoan.handmadeshop.infrastructure.persistence.jpa.BaseJpaEntity;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.category.entity.CategoryJpaEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
public class ProductJpaEntity extends BaseJpaEntity {

    private String name;
    private String description;
    private BigDecimal basePrice;

    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryJpaEntity category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariantJpaEntity> variants = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImageJpaEntity> images = new ArrayList<>();
}