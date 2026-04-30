package com.nttoan.handmadeshop.domain.catalog.product.entity;

import java.math.BigDecimal;

public class ProductVariant {
    private String sku;
    private String colorId;
    private String size;
    private int stock;
    private BigDecimal priceAdjustment;
    private boolean active;
    public ProductVariant(String sku, String colorId, String size, int stock, BigDecimal priceAdjustment,
            boolean active) {
        this.sku = sku;
        this.colorId = colorId;
        this.size = size;
        this.stock = stock;
        this.priceAdjustment = priceAdjustment;
        this.active = active;
    }

    public void reduceStock(int quantity){
        if(stock < quantity){
             throw new IllegalArgumentException("Not enough stock");
        }
        this.stock -= quantity;
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
