package com.nttoan.handmadeshop.domain.catalog.category.entity;

import java.util.ArrayList;
import java.util.List;

import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductEntity;
import com.nttoan.handmadeshop.domain.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;
}
