package com.nttoan.handmadeshop.domain.catalog.product.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductVariantRequest {

    private UUID id;

    @NotBlank
    private String sku;
    private UUID colorId;
    private String size;

    @Nonnull
    @Min(0)
    private Integer stock;

    private BigDecimal priceAdjustment;
}
