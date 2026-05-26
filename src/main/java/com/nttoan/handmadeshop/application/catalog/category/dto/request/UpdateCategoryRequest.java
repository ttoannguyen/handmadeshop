package com.nttoan.handmadeshop.application.catalog.category.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateCategoryRequest {
    private String name;
    private String description;
}
