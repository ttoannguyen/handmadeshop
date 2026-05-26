package com.nttoan.handmadeshop.application.catalog.product.dto.response;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public class CreateProductResponse {
    public String id;
    public String name;
    public String description;
    public BigDecimal basePrice;
    public String categoryId;
    public boolean active;
}
