package com.nttoan.handmadeshop.domain.catalog.product.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductImageRequest {
    private UUID id;
    @NotBlank
    private String imageUrl;
    private Boolean isPrimary;
    private Integer displayOrder;
}
