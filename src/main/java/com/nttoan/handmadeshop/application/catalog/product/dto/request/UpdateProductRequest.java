package com.nttoan.handmadeshop.application.catalog.product.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {
    private String name;

    private String description;

    @PositiveOrZero
    private BigDecimal basePrice;
}
