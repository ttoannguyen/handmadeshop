package com.nttoan.handmadeshop.domain.catalog.category.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank
    private String name;    
    private UUID parentId;
}
