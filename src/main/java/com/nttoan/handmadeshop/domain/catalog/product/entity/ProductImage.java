package com.nttoan.handmadeshop.domain.catalog.product.entity;

import com.nttoan.handmadeshop.domain.common.entity.BaseEntity;

public class ProductImage extends BaseEntity {
    private String imageUrl;
    private boolean primary;
    private int displayOrder;

    public ProductImage(String imageUrl, boolean primary, int displayOrder) {
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("Image URL is required");
        }
        if (displayOrder < 0) {
            throw new IllegalArgumentException("Display order must >= 0");
        }

        this.imageUrl = imageUrl;
        this.primary = primary;
        this.displayOrder = displayOrder;
    }

    public void markAsPrimary() {
        this.primary = true;
    }

    public void unmarkPrimary() {
        this.primary = false;
    }

    public void changeDisplayOrder(int order) {
        if (order < 0) {
            throw new IllegalArgumentException("Display order must >= 0");
        }
        this.displayOrder = order;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isPrimary() {
        return primary;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

}
