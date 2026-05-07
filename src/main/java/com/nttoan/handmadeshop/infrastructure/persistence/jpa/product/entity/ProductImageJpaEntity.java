package com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.entity;

import com.nttoan.handmadeshop.infrastructure.persistence.jpa.BaseJpaEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_images")
@Getter @Setter
public class ProductImageJpaEntity extends BaseJpaEntity {

    private String imageUrl;
    private boolean primary;
    private int displayOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;
}