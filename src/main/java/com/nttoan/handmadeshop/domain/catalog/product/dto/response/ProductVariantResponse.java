package com.nttoan.handmadeshop.domain.catalog.product.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductVariantResponse {
    private UUID id;

    private String sku;
    private String size;
    private Integer stock;
    private BigDecimal priceAdjustment;

    private UUID colorId;
    private String colorName;
    private String hexCode;
}
