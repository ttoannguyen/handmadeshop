package com.nttoan.handmadeshop.domain.catalog.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductImageRequest {
    @NotBlank
    private String imageUrl;
    private Boolean isPrimary;
    private Integer displayOrder;
}
