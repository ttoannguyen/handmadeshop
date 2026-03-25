package com.nttoan.handmadeshop.domain.catalog.product.dto.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal basePrice;

    private boolean active;

    private List<CategorySimpleResponse> categories;
    private List<ProductVariantResponse> variants;
    private List<ProductImageResponse> images;

}
