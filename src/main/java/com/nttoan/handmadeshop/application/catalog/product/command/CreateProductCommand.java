package com.nttoan.handmadeshop.application.catalog.product.command;

import java.math.BigDecimal;
import java.util.List;

import com.nttoan.handmadeshop.application.catalog.product.dto.request.CreateImageRequest;
import com.nttoan.handmadeshop.application.catalog.product.dto.request.CreateVariantRequest;

public record CreateProductCommand(
        String name,
        String description,
        BigDecimal basePrice,
        String categoryId,
        List<CreateVariantRequest> variants,
        List<CreateImageRequest> images) {
}