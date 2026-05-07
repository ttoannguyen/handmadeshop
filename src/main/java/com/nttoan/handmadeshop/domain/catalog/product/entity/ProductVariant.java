package com.nttoan.handmadeshop.domain.catalog.product.entity;

import java.math.BigDecimal;

public class ProductVariant {
    private String sku;
    private String colorId;
    private String size;
    private int stock;
    private BigDecimal priceAdjustment;
    private boolean active;

    public ProductVariant(
            String sku,
            String colorId,
            String size,
            int stock,
            BigDecimal priceAdjustment) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU is required");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stock must >= 0");
        }
        if (priceAdjustment == null) {
            priceAdjustment = BigDecimal.ZERO;
        }

        this.sku = sku;
        this.colorId = colorId;
        this.size = size;
        this.stock = stock;
        this.priceAdjustment = priceAdjustment;
        this.active = true;
    }

      public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must > 0");
        }
        this.stock += quantity;
    }

    public void reduceStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must > 0");
        }
        if (this.stock < quantity) {
            throw new IllegalStateException("Not enough stock");
        }
        this.stock -= quantity;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public BigDecimal calculateFinalPrice(BigDecimal basePrice) {
        return basePrice.add(this.priceAdjustment);
    }

    public String getSku() {
        return sku;
    }

    public String getColorId() {
        return colorId;
    }

    public String getSize() {
        return size;
    }

    public int getStock() {
        return stock;
    }

    public BigDecimal getPriceAdjustment() {
        return priceAdjustment;
    }

    public boolean isActive() {
        return active;
    }


    
}
