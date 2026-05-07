package com.nttoan.handmadeshop.application.catalog.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateImageRequest {
    private String imageUrl;
    private boolean primary;
    private int displayOrder;
}
