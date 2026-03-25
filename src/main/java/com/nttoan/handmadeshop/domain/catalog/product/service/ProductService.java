package com.nttoan.handmadeshop.domain.catalog.product.service;

import com.nttoan.handmadeshop.domain.catalog.product.dto.request.ProductRequest;
import com.nttoan.handmadeshop.domain.catalog.product.dto.response.ProductResponse;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);
}
