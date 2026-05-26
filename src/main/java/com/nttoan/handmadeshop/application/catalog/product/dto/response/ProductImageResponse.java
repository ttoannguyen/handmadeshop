package com.nttoan.handmadeshop.application.catalog.product.dto.response;

import lombok.Builder;

@Builder
public class ProductImageResponse {
    public String id;
    public String imageUrl;
    public boolean primary;
    public Integer displayOrder;
}
