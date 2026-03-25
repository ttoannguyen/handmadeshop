package com.nttoan.handmadeshop.domain.catalog.product.dto.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    private String name;

    private String description;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal basePrice;

    private List<UUID> categoryIds;

    @NotEmpty
    private List<ProductVariantRequest> variants;

    private List<ProductImageRequest> images;
}
