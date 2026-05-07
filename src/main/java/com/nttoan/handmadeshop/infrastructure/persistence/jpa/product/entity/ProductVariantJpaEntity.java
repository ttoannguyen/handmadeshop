package com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.entity;

import java.math.BigDecimal;

import com.nttoan.handmadeshop.infrastructure.persistence.jpa.BaseJpaEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_variants")
@Getter @Setter
public class ProductVariantJpaEntity extends BaseJpaEntity {

    private String sku;
    private String colorId;
    private String size;
    private int stock;
    private BigDecimal priceAdjustment;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;
}
