package com.nttoan.handmadeshop.application.catalog.product.dto.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateProductRequest {
    private String name;
    private String description;
    private BigDecimal basePrice;
    private String categoryId;

    private List<CreateVariantRequest> variants;
    private List<CreateImageRequest> images;
}
