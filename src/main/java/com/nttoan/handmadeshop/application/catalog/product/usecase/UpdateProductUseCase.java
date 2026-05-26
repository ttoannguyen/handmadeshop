package com.nttoan.handmadeshop.application.catalog.product.usecase;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.catalog.product.dto.request.UpdateProductRequest;
import com.nttoan.handmadeshop.application.catalog.product.mapper.ProductAppMapper;
import com.nttoan.handmadeshop.application.catalog.product.dto.response.ProductResponse;
import com.nttoan.handmadeshop.application.common.dto.BaseResponse;
import com.nttoan.handmadeshop.domain.catalog.product.entity.Product;
import com.nttoan.handmadeshop.domain.catalog.product.repository.ProductRepository;

@Service
public class UpdateProductUseCase {

    private final ProductRepository productRepository;

    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public BaseResponse<ProductResponse> execute(String id, UpdateProductRequest request) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Product not found"));

            product.updateInfo(request.getName(), request.getDescription(), request.getBasePrice());

            Product saved = productRepository.save(product);
            return BaseResponse.success(ProductAppMapper.toResponse(saved));
        } catch (NoSuchElementException e) {
            return BaseResponse.notFound(e.getMessage());
        } catch (IllegalArgumentException e) {
            return BaseResponse.error("VALIDATION_ERROR", e.getMessage(), 400, null);
        } catch (Exception e) {
            return BaseResponse.error("ERROR", "Failed to update product", 500, e.getMessage());
        }
    }
}
