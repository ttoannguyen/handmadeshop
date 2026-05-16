package com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.entity;

import com.nttoan.handmadeshop.infrastructure.persistence.jpa.BaseJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_images")
@Getter
@Setter
public class ProductImageJpaEntity extends BaseJpaEntity {

    private String imageUrl;
    @Column(name = "is_primary")
    private boolean isPrimary;
    private int displayOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;
}