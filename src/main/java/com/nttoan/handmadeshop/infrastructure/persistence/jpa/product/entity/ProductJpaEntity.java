package com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.entity;

import java.math.BigDecimal;
import java.util.List;

import com.nttoan.handmadeshop.infrastructure.persistence.jpa.BaseJpaEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter @Setter
public class ProductJpaEntity extends BaseJpaEntity {

    private String name;
    private String description;
    private BigDecimal basePrice;
    private String categoryId;
    private boolean active;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductVariantJpaEntity> variants;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImageJpaEntity> images;
}