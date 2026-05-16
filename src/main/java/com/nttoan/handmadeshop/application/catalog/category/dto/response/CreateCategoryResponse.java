package com.nttoan.handmadeshop.application.catalog.category.dto.response;

import lombok.Builder;

@Builder
public class CreateCategoryResponse {
    public String name;
    public String description;
    public boolean active;
}
