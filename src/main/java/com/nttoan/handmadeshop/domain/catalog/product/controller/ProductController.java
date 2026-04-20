package com.nttoan.handmadeshop.domain.catalog.product.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.domain.catalog.product.dto.request.ProductRequest;
import com.nttoan.handmadeshop.domain.catalog.product.dto.response.ProductResponse;
import com.nttoan.handmadeshop.domain.catalog.product.service.ProductService;
import com.nttoan.handmadeshop.domain.common.dto.BaseResponse;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/products", produces = "application/json")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @SecurityRequirement(name = "bearerAuth")
    public BaseResponse<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        return BaseResponse.of(productService.createProduct(request));
    }

    @GetMapping
    @SecurityRequirement(name = "bearerAuth")

    public BaseResponse<List<ProductResponse>> getProducts() {
        return BaseResponse.of(productService.getProducts());
    }

    @GetMapping("/{productId}")
    public BaseResponse<ProductResponse> getProduct(@PathVariable("productId") UUID productId) {
        return BaseResponse.of(productService.getProduct(productId));
    }

    @PatchMapping("/{productId}/deactivate")
    @SecurityRequirement(name = "bearerAuth")

    public BaseResponse<Void> deactivateProduct(@PathVariable UUID productId) {
        productService.deactivateProduct(productId);
        return BaseResponse.ok();
    }

    @PatchMapping("/{productId}/activate")
    @SecurityRequirement(name = "bearerAuth")
    public BaseResponse<Void> activateProduct(@PathVariable UUID productId) {
        productService.activateProduct(productId);
        return BaseResponse.ok();
    }
}
