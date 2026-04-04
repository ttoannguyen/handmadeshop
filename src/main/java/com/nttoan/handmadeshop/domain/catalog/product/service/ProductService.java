package com.nttoan.handmadeshop.domain.catalog.product.service;

import java.util.List;
import java.util.UUID;

import com.nttoan.handmadeshop.domain.catalog.product.dto.request.ProductRequest;
import com.nttoan.handmadeshop.domain.catalog.product.dto.response.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    ProductResponse updateProduct(UUID productId, ProductRequest request);

    ProductResponse getProduct(UUID productId);

    List<ProductResponse> getProducts();
}
