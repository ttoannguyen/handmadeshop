package com.nttoan.handmadeshop.application.catalog.product.dto.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateVariantRequest {
    private String sku;
    private String colorId;
    private String size;
    private int stock;
    private BigDecimal priceAdjustment;
}
