package com.nttoan.handmadeshop.application.catalog.product.usecase;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.catalog.product.dto.response.ProductResponse;
import com.nttoan.handmadeshop.application.common.dto.BaseResponse;
import com.nttoan.handmadeshop.application.catalog.product.mapper.ProductAppMapper;
import com.nttoan.handmadeshop.domain.catalog.product.repository.ProductRepository;

@Service
public class ListProductsUseCase {

    private final ProductRepository productRepository;

    public ListProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public BaseResponse<java.util.List<ProductResponse>> execute() {
        try {
            java.util.List<ProductResponse> list = productRepository.findAll().stream()
                    .map(ProductAppMapper::toResponse).collect(Collectors.toList());
            return BaseResponse.success(list);
        } catch (Exception e) {
            return BaseResponse.error("ERROR", "Failed to list products", 500, e.getMessage());
        }
    }
}
