package com.nttoan.handmadeshop.domain.catalog.product.entity;

import com.nttoan.handmadeshop.domain.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "colors")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColorEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String hexCode;
}
