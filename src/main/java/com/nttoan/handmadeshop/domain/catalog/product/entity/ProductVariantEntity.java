package com.nttoan.handmadeshop.domain.catalog.product.entity;

import java.math.BigDecimal;

import com.nttoan.handmadeshop.domain.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_variants", uniqueConstraints = @UniqueConstraint(name = "uk_sku", columnNames = "sku"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private String sku;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private ColorEntity color;

    @Column(precision = 15, scale = 2)
    private BigDecimal priceAdjustment;

    @Column(nullable = false)
    @Builder.Default
    private boolean isActive = true;

}
