package com.nttoan.handmadeshop.application.catalog.product.usecase;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.catalog.product.dto.request.CreateImageRequest;
import com.nttoan.handmadeshop.application.catalog.product.dto.request.CreateProductRequest;
import com.nttoan.handmadeshop.application.catalog.product.dto.request.CreateVariantRequest;
import com.nttoan.handmadeshop.domain.catalog.product.entity.Product;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductImage;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductVariant;
import com.nttoan.handmadeshop.domain.catalog.product.repository.ProductRepository;

@Service
public class CreateProductUseCase {

    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(CreateProductRequest request) {

        Product product = new Product(
                request.getName(),
                request.getDescription(),
                request.getBasePrice(),
                request.getCategoryId()
        );

        // variants
        if (request.getVariants() != null) {
            for (CreateVariantRequest v : request.getVariants()) {
                ProductVariant variant = new ProductVariant(
                        v.getSku(),
                        v.getColorId(),
                        v.getSize(),
                        v.getStock(),
                        v.getPriceAdjustment()
                );
                product.addVariant(variant);
            }
        }

        // images
        if (request.getImages() != null) {
            for (CreateImageRequest i : request.getImages()) {
                ProductImage image = new ProductImage(
                        i.getImageUrl(),
                        i.isPrimary(),
                        i.getDisplayOrder()
                );
                product.addImage(image);
            }
        }

        return productRepository.save(product);
    }
}