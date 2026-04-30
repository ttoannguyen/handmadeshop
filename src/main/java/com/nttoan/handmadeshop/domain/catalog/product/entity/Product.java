package com.nttoan.handmadeshop.domain.catalog.product.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.nttoan.handmadeshop.domain.common.entity.BaseEntity;

public class Product extends BaseEntity{

    private String name;
    private String description;
    private BigDecimal basePrice;
    private String categoryId;
    private boolean active;

    private final List<ProductVariant> variants = new ArrayList<>(); 
    private final List<ProductImage> images = new ArrayList<>();
    public Product(String name, String description, BigDecimal basePrice, String categoryId, boolean active) {
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.categoryId = categoryId;
        this.active = active;
    }

    public void addVariant(ProductVariant variants){
        this.variants.add(variants);
    }

    public void addImage(ProductImage images){
        this.images.add(images);
    }

    public void deactive(){
        this.active = false;
    }
    
    public void active(){
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
