package com.nttoan.handmadeshop.domain.catalog.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ColorRequest {
    @NotBlank
    private String name;
    private String hexCode;
}
