package com.nttoan.handmadeshop.domain.catalog.product.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    @Transactional
    public ProductResponse updateProduct(UUID productId, ProductRequest request) {
        validateRequest(request);

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setBasePrice(request.getBasePrice());

        syncCategories(product, request.getCategoryIds());

        syncVariant(product, request.getVariants());

        syncImages(product, request.getImages());

        return ProductResponse.builder().build();
    }

    @Override
    @Transactional
    public void deactivateProduct(UUID productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (!Boolean.TRUE.equals(product.isActive()))
            throw new RuntimeException("Product already inactive");
        product.setActive(false);
    }

    @Override
    public void activateProduct(UUID productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setActive(false);
    }

    @Override
    public ProductResponse getProduct(UUID productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getProducts() {
        return productMapper.toResponseList(productRepository.findAll());
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

    private void syncCategories(ProductEntity product, List<UUID> categoryIds) {
        Set<UUID> newIds = new HashSet<>(categoryIds);
        Map<UUID, ProductCategoryEntity> existingMap = product.getProductCategories()
                .stream()
                .collect(Collectors.toMap(pc -> pc.getCategory().getId(), pc -> pc));

        for (UUID categoryId : categoryIds) {
            if (!existingMap.containsKey(categoryId)) {
                CategoryEntity category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category not found"));
                ProductCategoryEntity pc = ProductCategoryEntity.builder()
                        .product(product)
                        .category(category)
                        .build();

                product.getProductCategories().add(pc);
            }
        }

        product.getProductCategories().removeIf(pc -> !newIds.contains(pc.getCategory().getId()));

    }

    private void syncVariant(ProductEntity product, List<ProductVariantRequest> requests) {

        if (requests == null || requests.isEmpty()) {
            throw new RuntimeException("Product must have at least 1 variant");
        }

        Map<UUID, ProductVariantEntity> existingMap = product.getVariants().stream()
                .collect(Collectors.toMap(ProductVariantEntity::getId, v -> v));
        Set<String> uniqueKey = new HashSet<>();

        for (ProductVariantRequest req : requests) {
            // Validate duplicate color + size
            String key = (req.getColorId() != null ? req.getColorId() : "null") + "_"
                    + (req.getSize() != null ? req.getSize() : "null");
            if (!uniqueKey.add(key))
                throw new RuntimeException("Duplicate variant (color + size)");

            // Validate stock
            if (req.getStock() < 0) {
                throw new RuntimeException("Stock cannot be negative");
            }

            // Validate SKU - exclude itself
            if (req.getId() == null) {
                if (variantRepository.existsBySku(req.getSku())) {
                    throw new RuntimeException("SKU already exists: " + req.getSku());
                }
            } else {
                if (variantRepository.existsBySkuAndIdNot(req.getSku(), req.getId())) {
                    throw new RuntimeException("SKU already exists: " + req.getSku());
                }
            }

            ColorEntity color = null;
            if (req.getColorId() != null) {
                color = colorRepository.findById(req.getColorId())
                        .orElseThrow(() -> new RuntimeException("Color not found"));
            }

            // Validate price
            if (req.getPriceAdjustment() != null
                    && product.getBasePrice().add(req.getPriceAdjustment()).compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("Invalid price (base + adjustment < 0)");
            }

            if (req.getId() != null) {
                // update
                ProductVariantEntity existing = existingMap.get(req.getId());

                if (existing == null)
                    throw new RuntimeException("Variant not found: " + req.getId());

                existing.setSku(req.getSku());
                existing.setColor(color);
                existing.setSize(req.getSize());
                existing.setStock(req.getStock());
                existing.setPriceAdjustment(req.getPriceAdjustment());

                existingMap.remove(req.getId());
            } else {
                // create
                ProductVariantEntity newVariant = ProductVariantEntity.builder()
                        .product(product)
                        .color(color)
                        .priceAdjustment(req.getPriceAdjustment())
                        .size(req.getSize())
                        .stock(req.getStock())
                        .sku(req.getSku())
                        .build();

                product.getVariants().add(newVariant);
            }
        }
        for (ProductVariantEntity toDelete : existingMap.values()) {
            product.getVariants().remove(toDelete);
        }
    }

    private void syncImages(ProductEntity product, List<ProductImageRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            product.getProductImages().clear();
            return;
        }

        boolean hasPrimary = false;
        for (ProductImageRequest img : requests) {
            if (Boolean.TRUE.equals(img.getIsPrimary())) {
                if (hasPrimary)
                    throw new RuntimeException("Only one primary image allowed");
                hasPrimary = true;
            }
        }

        if (!hasPrimary)
            throw new RuntimeException("Product must have a primary image");

        Map<UUID, ProductImageEntity> existingMap = product.getProductImages().stream()
                .collect(Collectors.toMap(pi -> pi.getId(), pi -> pi));

        Set<UUID> processedIds = new HashSet<>();
        for (ProductImageRequest req : requests) {
            if (req.getId() != null) {
                ProductImageEntity existing = existingMap.get(req.getId());
                if (existing == null)
                    throw new RuntimeException("Image not found: " + req.getId());

                existing.setImageUrl(req.getImageUrl());
                existing.setIsPrimary(Boolean.TRUE.equals(req.getIsPrimary()));
                existing.setDisplayOrder(req.getDisplayOrder() != null ? req.getDisplayOrder() : 0);

                processedIds.add(req.getId());
            } else {
                ProductImageEntity newImage = ProductImageEntity.builder()
                        .product(product)
                        .imageUrl(req.getImageUrl())
                        .displayOrder(req.getDisplayOrder() != null ? req.getDisplayOrder() : 0)
                        .isPrimary(Boolean.TRUE.equals(req.getIsPrimary()))
                        .build();

                product.getProductImages().add(newImage);
            }
        }

        product.getProductImages().removeIf(img -> img.getId() != null && !processedIds.contains(img.getId()));
    }

}
