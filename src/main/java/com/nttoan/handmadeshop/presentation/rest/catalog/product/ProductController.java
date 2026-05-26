package com.nttoan.handmadeshop.presentation.rest.catalog.product;

import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.application.catalog.product.dto.request.CreateProductRequest;
import com.nttoan.handmadeshop.application.catalog.product.dto.request.UpdateProductRequest;
import com.nttoan.handmadeshop.application.catalog.product.dto.response.CreateProductResponse;
import com.nttoan.handmadeshop.application.catalog.product.dto.response.ProductResponse;
import com.nttoan.handmadeshop.application.catalog.product.usecase.CreateProductUseCase;
import com.nttoan.handmadeshop.application.catalog.product.usecase.GetProductUseCase;
import com.nttoan.handmadeshop.application.catalog.product.usecase.UpdateProductUseCase;
import com.nttoan.handmadeshop.application.catalog.product.usecase.DeleteProductUseCase;
import com.nttoan.handmadeshop.application.catalog.product.usecase.ListProductsUseCase;
import com.nttoan.handmadeshop.application.common.dto.BaseResponse;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final CreateProductUseCase createUseCase;
    private final GetProductUseCase getUseCase;
    private final UpdateProductUseCase updateUseCase;
    private final DeleteProductUseCase deleteUseCase;
    private final ListProductsUseCase listUseCase;

    public ProductController(CreateProductUseCase createUseCase,
            GetProductUseCase getUseCase,
            UpdateProductUseCase updateUseCase,
            DeleteProductUseCase deleteUseCase,
            ListProductsUseCase listUseCase) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
        this.listUseCase = listUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<CreateProductResponse> create(@Valid @RequestBody CreateProductRequest request) {
        return createUseCase.execute(request);
    }

    @GetMapping
    public BaseResponse<java.util.List<ProductResponse>> getProducts() {
        return listUseCase.execute();
    }

    @GetMapping("/{productId}")
    public BaseResponse<ProductResponse> getProduct(@PathVariable String productId) {
        return getUseCase.execute(productId);
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<ProductResponse> updateProduct(@PathVariable String productId,
            @Valid @RequestBody UpdateProductRequest request) {
        return updateUseCase.execute(productId, request);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public BaseResponse<Void> deleteProduct(@PathVariable String productId) {
        return deleteUseCase.execute(productId);
    }
}