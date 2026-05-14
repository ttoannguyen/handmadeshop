package com.nttoan.handmadeshop.presentation.rest.catalog.product;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.application.catalog.product.dto.request.CreateProductRequest;
import com.nttoan.handmadeshop.application.catalog.product.usecase.CreateProductUseCase;
import com.nttoan.handmadeshop.application.common.dto.BaseResponse;
import com.nttoan.handmadeshop.domain.catalog.product.entity.Product;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase useCase;

    public ProductController(CreateProductUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public BaseResponse<Product> create(@RequestBody CreateProductRequest request) {
        Product product = useCase.execute(request);
        return BaseResponse.success(product);
    }
}