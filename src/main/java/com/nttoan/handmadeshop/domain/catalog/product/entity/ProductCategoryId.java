package com.nttoan.handmadeshop.domain.catalog.product.entity;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductCategoryId implements Serializable {
    private UUID productId;
    private UUID categoryId;
}
