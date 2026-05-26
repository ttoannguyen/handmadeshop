package com.nttoan.handmadeshop.application.catalog.product.mapper;

import java.util.stream.Collectors;

import com.nttoan.handmadeshop.application.catalog.product.command.CreateProductCommand;
import com.nttoan.handmadeshop.application.catalog.product.dto.request.CreateProductRequest;
import com.nttoan.handmadeshop.application.catalog.product.dto.response.ProductImageResponse;
import com.nttoan.handmadeshop.application.catalog.product.dto.response.ProductResponse;
import com.nttoan.handmadeshop.application.catalog.product.dto.response.ProductVariantResponse;
import com.nttoan.handmadeshop.domain.catalog.product.entity.Product;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductImage;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductVariant;

public class ProductAppMapper {
    public static CreateProductCommand toCommand(CreateProductRequest request) {
        return new CreateProductCommand(
                request.getName(),
                request.getDescription(),
                request.getBasePrice(),
                request.getCategoryId(),
                request.getVariants(),
                request.getImages());
    }

    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .active(product.isActive())
                .basePrice(product.getBasePrice())
                .variants(product.getVariants()
                        .stream().map(ProductAppMapper::toVariantResponse).collect(Collectors.toList()))
                .images(product.getImages().stream().map(ProductAppMapper::toImageResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public static ProductVariantResponse toVariantResponse(ProductVariant variant) {
        return ProductVariantResponse.builder()
                .id(variant.getId())
                .sku(variant.getSku())
                .colorId(variant.getColorId())
                .size(variant.getSize())
                .stock(variant.getStock())
                .priceAdjustment(variant.getPriceAdjustment())
                .build();
    }

    private static ProductImageResponse toImageResponse(ProductImage image) {
        return ProductImageResponse.builder()
                .id(image.getId())
                .imageUrl(image.getImageUrl())
                .displayOrder(image.getDisplayOrder())
                .primary(image.isPrimary())
                .build();
    }
}
