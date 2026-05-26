package com.nttoan.handmadeshop.application.catalog.product.dto.request;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    @PositiveOrZero
    private BigDecimal basePrice;

    @NotBlank
    private String categoryId;

    @Valid
    private List<CreateVariantRequest> variants;

    @Valid
    private List<CreateImageRequest> images;
}
