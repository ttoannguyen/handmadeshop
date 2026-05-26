package com.nttoan.handmadeshop.application.catalog.product.dto.response;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public class ProductVariantResponse {

    public String id;
    public String sku;
    public String colorId;
    public String size;
    public Integer stock;
    public BigDecimal priceAdjustment;
}