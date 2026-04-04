package com.nttoan.handmadeshop.domain.catalog.category.dto.response;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class CategoryResponse {
    private UUID id;
    private String name;
    private String slug;
    private UUID parentId;
    private String parentName;
    private List<CategoryResponse> children;
}
