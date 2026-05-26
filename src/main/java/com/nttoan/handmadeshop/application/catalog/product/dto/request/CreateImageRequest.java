package com.nttoan.handmadeshop.application.catalog.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateImageRequest {
    @NotBlank
    private String imageUrl;

    private boolean primary;

    @PositiveOrZero
    private int displayOrder;
}
