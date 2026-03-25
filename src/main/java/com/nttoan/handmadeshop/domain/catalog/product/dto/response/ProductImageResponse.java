package com.nttoan.handmadeshop.domain.catalog.product.dto.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductImageResponse {

    private UUID id;
    private String imageUrl;
    private boolean isPrimary;
    private Integer displayOrder;
}
