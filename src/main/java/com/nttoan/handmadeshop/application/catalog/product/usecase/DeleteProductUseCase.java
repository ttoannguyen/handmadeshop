package com.nttoan.handmadeshop.application.catalog.product.usecase;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.domain.catalog.product.entity.Product;
import com.nttoan.handmadeshop.domain.catalog.product.repository.ProductRepository;
import com.nttoan.handmadeshop.application.common.dto.BaseResponse;

@Service
public class DeleteProductUseCase {

    private final ProductRepository productRepository;

    public DeleteProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public BaseResponse<Void> execute(String id) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Product not found"));

            product.deactive();
            productRepository.save(product);
            return BaseResponse.success(null);
        } catch (NoSuchElementException e) {
            return BaseResponse.notFound(e.getMessage());
        } catch (Exception e) {
            return BaseResponse.error("ERROR", "Failed to delete product", 500, e.getMessage());
        }
    }
}
