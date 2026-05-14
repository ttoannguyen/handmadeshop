package com.nttoan.handmadeshop.presentation.rest.catalog.category;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.application.catalog.category.dto.request.CreateCategoryRequest;
import com.nttoan.handmadeshop.application.catalog.category.usecase.CreateCategoryUseCase;
import com.nttoan.handmadeshop.application.common.dto.BaseResponse;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CreateCategoryUseCase useCase;

    public CategoryController(CreateCategoryUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public BaseResponse create(@RequestBody CreateCategoryRequest request) {
        return BaseResponse.success(useCase.execute(request));
    }
}
