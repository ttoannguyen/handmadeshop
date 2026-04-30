package com.nttoan.handmadeshop.domain.catalog.product.entity;

public class ProductImage {
    private String imageUrl;
    private boolean primary;
    private int displayOrder;
    public ProductImage(String imageUrl, boolean primary, int displayOrder) {
        this.imageUrl = imageUrl;
        this.primary = primary;
        this.displayOrder = displayOrder;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public boolean isPrimary() {
        return primary;
    }
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
    public int getDisplayOrder() {
        return displayOrder;
    }
    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
   
}
