package com.nttoan.handmadeshop.infrastructure.persistence.jpa.catalog.category.entity;

import com.nttoan.handmadeshop.infrastructure.persistence.jpa.BaseJpaEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class CategoryJpaEntity extends BaseJpaEntity {
    private String name;
    private String slug;

    @Column(name = "parent_id")
    private String parentId;

    private boolean active;
}
