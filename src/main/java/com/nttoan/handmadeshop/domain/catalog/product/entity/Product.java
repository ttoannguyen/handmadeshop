package com.nttoan.handmadeshop.domain.catalog.product.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.nttoan.handmadeshop.domain.common.entity.BaseEntity;

public class Product extends BaseEntity {

    private String name;
    private String description;
    private BigDecimal basePrice;
    private String categoryId;
    private boolean active;

    private final List<ProductVariant> variants = new ArrayList<>();
    private final List<ProductImage> images = new ArrayList<>();

    public Product(String name, String description, BigDecimal basePrice, String categoryId) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (basePrice == null || basePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be >= 0");
        }

        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.categoryId = categoryId;
        this.active = true; // mặc định active
    }

    public void addVariant(ProductVariant variant) {
        if (!this.active) {
            throw new IllegalStateException("Cannot add variant to inactive product");
        }
        this.variants.add(variant);
    }

    public void updateInfo(String name, String description, BigDecimal basePrice) {
        if (name != null && !name.isBlank()) {
            this.name = name;
        }
        if (basePrice != null && basePrice.compareTo(BigDecimal.ZERO) >= 0) {
            this.basePrice = basePrice;
        }
        this.description = description;
    }

    public void addImage(ProductImage images) {
        this.images.add(images);
    }

    public void deactive() {
        this.active = false;
    }

    public void active() {
        this.active = true;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public boolean isActive() {
        return active;
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }

    public List<ProductImage> getImages() {
        return images;
    }

}
