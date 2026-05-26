package com.nttoan.handmadeshop.presentation.rest.catalog.category;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.application.catalog.category.dto.request.CreateCategoryRequest;
import com.nttoan.handmadeshop.application.catalog.category.dto.request.UpdateCategoryRequest;
import com.nttoan.handmadeshop.application.catalog.category.dto.response.CreateCategoryResponse;
import com.nttoan.handmadeshop.application.catalog.category.usecase.CreateCategoryUseCase;
import com.nttoan.handmadeshop.application.catalog.category.usecase.DeleteCategoryUseCase;
import com.nttoan.handmadeshop.application.catalog.category.usecase.GetCategoryUseCase;
import com.nttoan.handmadeshop.application.catalog.category.usecase.ListCategoriesUseCase;
import com.nttoan.handmadeshop.application.catalog.category.usecase.UpdateCategoryUseCase;
import com.nttoan.handmadeshop.application.common.dto.BaseResponse;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CreateCategoryUseCase createUseCase;
    private final GetCategoryUseCase getUseCase;
    private final UpdateCategoryUseCase updateUseCase;
    private final DeleteCategoryUseCase deleteUseCase;
    private final ListCategoriesUseCase listUseCase;

    public CategoryController(CreateCategoryUseCase createUseCase, GetCategoryUseCase getUseCase,
            UpdateCategoryUseCase updateUseCase, DeleteCategoryUseCase deleteUseCase,
            ListCategoriesUseCase listUseCase) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
        this.listUseCase = listUseCase;
    }

    @PostMapping
    public BaseResponse<CreateCategoryResponse> create(@RequestBody CreateCategoryRequest request) {
        return createUseCase.execute(request);
    }

    @GetMapping
    public BaseResponse<List<CreateCategoryResponse>> list() {
        return listUseCase.execute();
    }

    @GetMapping("/{id}")
    public BaseResponse<CreateCategoryResponse> get(@PathVariable String id) {
        return getUseCase.execute(id);
    }

    @PutMapping("/{id}")
    public BaseResponse<CreateCategoryResponse> update(@PathVariable String id,
            @RequestBody UpdateCategoryRequest request) {
        return updateUseCase.execute(id, request);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> delete(@PathVariable String id) {
        return deleteUseCase.execute(id);
    }

}
