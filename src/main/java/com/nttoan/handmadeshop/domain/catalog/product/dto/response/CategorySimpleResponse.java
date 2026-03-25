package com.nttoan.handmadeshop.domain.catalog.product.dto.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategorySimpleResponse {
    private UUID id;
    private String name;
    private String slug;
}
