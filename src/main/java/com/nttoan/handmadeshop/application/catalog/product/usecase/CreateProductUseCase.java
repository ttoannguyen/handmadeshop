package com.nttoan.handmadeshop.application.catalog.product.usecase;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.catalog.product.dto.request.CreateImageRequest;
import com.nttoan.handmadeshop.application.catalog.product.dto.request.CreateProductRequest;
import com.nttoan.handmadeshop.application.catalog.product.dto.request.CreateVariantRequest;
import com.nttoan.handmadeshop.application.catalog.product.dto.response.CreateProductResponse;
import com.nttoan.handmadeshop.domain.catalog.product.entity.Product;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductImage;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductVariant;
import com.nttoan.handmadeshop.domain.catalog.product.repository.ProductRepository;
import com.nttoan.handmadeshop.application.common.dto.BaseResponse;

@Service
public class CreateProductUseCase {

    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public BaseResponse<CreateProductResponse> execute(CreateProductRequest request) {
        try {
            Product product = new Product(
                    request.getName(),
                    request.getDescription(),
                    request.getBasePrice(),
                    request.getCategoryId());

            // variants
            if (request.getVariants() != null) {
                for (CreateVariantRequest v : request.getVariants()) {
                    ProductVariant variant = new ProductVariant(
                            v.getSku(),
                            v.getColorId(),
                            v.getSize(),
                            v.getStock(),
                            v.getPriceAdjustment());
                    product.addVariant(variant);
                }
            }

            // images
            if (request.getImages() != null) {
                for (CreateImageRequest i : request.getImages()) {
                    ProductImage image = new ProductImage(
                            i.getImageUrl(),
                            i.isPrimary(),
                            i.getDisplayOrder());
                    product.addImage(image);
                }
            }

            Product savedProduct = productRepository.save(product);

            CreateProductResponse resp = CreateProductResponse.builder()
                    .id(savedProduct.getId())
                    .name(savedProduct.getName())
                    .description(savedProduct.getDescription())
                    .basePrice(savedProduct.getBasePrice())
                    .categoryId(savedProduct.getCategoryId())
                    .active(savedProduct.isActive())
                    .build();

            return BaseResponse.created(resp);
        } catch (IllegalArgumentException e) {
            return BaseResponse.error("VALIDATION_ERROR", e.getMessage(), 400, null);
        } catch (Exception e) {
            return BaseResponse.error("ERROR", "Failed to create product", 500, e.getMessage());
        }
    }
}