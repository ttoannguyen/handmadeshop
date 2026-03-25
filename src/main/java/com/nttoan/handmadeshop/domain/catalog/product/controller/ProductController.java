package com.nttoan.handmadeshop.domain.catalog.product.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.domain.catalog.product.dto.request.ProductRequest;
import com.nttoan.handmadeshop.domain.catalog.product.dto.response.ProductResponse;
import com.nttoan.handmadeshop.domain.catalog.product.service.ProductService;
import com.nttoan.handmadeshop.domain.common.dto.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public BaseResponse<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        return BaseResponse.of(productService.createProduct(request));
    }
}
