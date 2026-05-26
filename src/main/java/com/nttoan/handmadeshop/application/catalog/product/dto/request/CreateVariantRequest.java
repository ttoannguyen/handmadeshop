package com.nttoan.handmadeshop.application.catalog.product.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateVariantRequest {
    @NotBlank
    private String sku;

    private String colorId;

    private String size;

    @PositiveOrZero
    private int stock;

    @NotNull
    private BigDecimal priceAdjustment;
}
