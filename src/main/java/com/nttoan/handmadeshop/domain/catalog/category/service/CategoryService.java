package com.nttoan.handmadeshop.domain.catalog.category.service;

import java.util.List;

import com.nttoan.handmadeshop.domain.catalog.category.dto.request.CategoryRequest;
import com.nttoan.handmadeshop.domain.catalog.category.dto.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse create(CategoryRequest request);

    List<CategoryResponse> getAll();

    List<CategoryResponse> getTree();

}
