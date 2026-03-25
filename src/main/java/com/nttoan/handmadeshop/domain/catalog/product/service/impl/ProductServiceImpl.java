package com.nttoan.handmadeshop.domain.catalog.product.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.domain.catalog.category.entity.CategoryEntity;
import com.nttoan.handmadeshop.domain.catalog.category.repository.CategoryRepository;
import com.nttoan.handmadeshop.domain.catalog.product.dto.request.ProductImageRequest;
import com.nttoan.handmadeshop.domain.catalog.product.dto.request.ProductRequest;
import com.nttoan.handmadeshop.domain.catalog.product.dto.request.ProductVariantRequest;
import com.nttoan.handmadeshop.domain.catalog.product.dto.response.ProductResponse;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ColorEntity;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductCategoryEntity;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductEntity;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductImageEntity;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductVariantEntity;
import com.nttoan.handmadeshop.domain.catalog.product.mapper.ProductMapper;
import com.nttoan.handmadeshop.domain.catalog.product.repository.ColorRepository;
import com.nttoan.handmadeshop.domain.catalog.product.repository.ProductRepository;
import com.nttoan.handmadeshop.domain.catalog.product.repository.ProductVariantRepository;
import com.nttoan.handmadeshop.domain.catalog.product.service.ProductService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ColorRepository colorRepository;
    private final CategoryRepository categoryRepository;
    private final ProductVariantRepository variantRepository;

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        validateRequest(request);
        ProductEntity product = ProductEntity.builder()
                .name(request.getName())
                .basePrice(request.getBasePrice())
                .description(request.getDescription())
                .build();

        attachCategories(product, request.getCategoryIds());

        attachVariants(product, request.getVariants());

        attachImages(product, request.getImages());

        return productMapper.toResponse(productRepository.save(product));
    }

    private void validateRequest(ProductRequest request) {
        if (request.getVariants() == null || request.getVariants().isEmpty()) {
            throw new RuntimeException("Product must have at least 1 variant");

        }
        request.getVariants().forEach(item -> {
            if (item.getStock() < 0)
                throw new RuntimeException("Stock cannot be negative");
        });
    }

    private void attachCategories(ProductEntity product, List<UUID> categoryIds) {
        if (categoryIds == null || categoryIds.isEmpty())
            return;

        List<CategoryEntity> categories = categoryRepository.findAllById(categoryIds);

        if (categories.size() != categoryIds.size())
            throw new RuntimeException("Some category not found");

        for (CategoryEntity category : categories) {
            ProductCategoryEntity pc = ProductCategoryEntity.builder()
                    .product(product)
                    .category(category)
                    .build();
            product.getProductCategories().add(pc);
        }
    }

    private void attachVariants(ProductEntity product, List<ProductVariantRequest> variants) {
        Set<String> uniqueVariantKey = new HashSet<>();

        for (ProductVariantRequest variant : variants) {
            if (variantRepository.existsBySku(variant.getSku())) {
                throw new RuntimeException("SKU already exists: " + variant.getSku());
            }

            String key = (variant.getColorId() != null ? variant.getColorId() : "null") + "_"
                    + (variant.getSize() != null ? variant.getSize() : "null");
            if (!uniqueVariantKey.add(key)) {
                throw new RuntimeException("Duplication variant (color + size)");
            }

            ColorEntity color = null;
            if (variant.getColorId() != null) {
                color = colorRepository.findById(variant.getColorId())
                        .orElseThrow(() -> new RuntimeException("Color not found"));

            }
            if (variant.getPriceAdjustment() != null
                    && product.getBasePrice().add(variant.getPriceAdjustment()).compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("Invalid price ( base + adjustment < 0)");
            }

            ProductVariantEntity productVariant = ProductVariantEntity.builder()
                    .color(color)
                    .product(product)
                    .priceAdjustment(variant.getPriceAdjustment())
                    .sku(variant.getSku())
                    .size(variant.getSize())
                    .stock(variant.getStock())
                    .build();
            product.getVariants().add(productVariant);
        }

    }

    private void attachImages(ProductEntity product, List<ProductImageRequest> images) {
        if (images == null || images.isEmpty())
            return;

        boolean hasPrimary = false;

        for (ProductImageRequest img : images) {
            if (img.getIsPrimary()) {
                if (hasPrimary)
                    throw new RuntimeException("Only one primary image allowed");
                hasPrimary = true;
            }
            ProductImageEntity productImage = ProductImageEntity.builder()
                    .product(product)
                    .imageUrl(img.getImageUrl())
                    .isPrimary(img.getIsPrimary())
                    .displayOrder(img.getDisplayOrder() != null ? img.getDisplayOrder() : 0)
                    .build();

            product.getProductImages().add(productImage);
        }
    }

}
