package com.nttoan.handmadeshop.application.catalog.product.dto.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

@Builder
public class ProductResponse {
    public String id;
    public String name;
    public String description;
    public BigDecimal basePrice;
    public String categoryId;
    public boolean active;

    public List<ProductVariantResponse> variants;
    public List<ProductImageResponse> images;
}
