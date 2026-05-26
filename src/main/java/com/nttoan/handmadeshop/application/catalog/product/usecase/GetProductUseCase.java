package com.nttoan.handmadeshop.application.catalog.product.usecase;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.catalog.product.mapper.ProductAppMapper;
import com.nttoan.handmadeshop.application.catalog.product.dto.response.ProductResponse;
import com.nttoan.handmadeshop.application.common.dto.BaseResponse;
import com.nttoan.handmadeshop.domain.catalog.product.repository.ProductRepository;

@Service
public class GetProductUseCase {

    private final ProductRepository productRepository;

    public GetProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public BaseResponse<ProductResponse> execute(String id) {
        try {
            return productRepository.findById(id)
                    .map(p -> BaseResponse.success(ProductAppMapper.toResponse(p)))
                    .orElseGet(() -> BaseResponse.notFound("Product not found"));
        } catch (Exception e) {
            return BaseResponse.error("ERROR", "Failed to get product", 500, e.getMessage());
        }
    }
}
