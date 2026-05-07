package com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.mapper;

import java.util.ArrayList;
import java.util.List;

import com.nttoan.handmadeshop.domain.catalog.product.entity.Product;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductImage;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ProductVariant;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.entity.ProductImageJpaEntity;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.entity.ProductJpaEntity;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.product.entity.ProductVariantJpaEntity;

public class ProductMapper {

    public static ProductJpaEntity toJpa(Product p) {

        ProductJpaEntity e = new ProductJpaEntity();

        e.setId(p.getId());
        e.setName(p.getName());
        e.setDescription(p.getDescription());
        e.setBasePrice(p.getBasePrice());
        e.setCategoryId(p.getCategoryId());
        e.setActive(p.isActive());

        // variants
        List<ProductVariantJpaEntity> variantEntities = new ArrayList<>();
        for (ProductVariant v : p.getVariants()) {
            ProductVariantJpaEntity ve = new ProductVariantJpaEntity();
            ve.setSku(v.getSku());
            ve.setColorId(v.getColorId());
            ve.setSize(v.getSize());
            ve.setStock(v.getStock());
            ve.setPriceAdjustment(v.getPriceAdjustment());
            ve.setActive(v.isActive());
            ve.setProduct(e);
            variantEntities.add(ve);
        }

        // images
        List<ProductImageJpaEntity> imageEntities = new ArrayList<>();
        for (ProductImage i : p.getImages()) {
            ProductImageJpaEntity ie = new ProductImageJpaEntity();
            ie.setImageUrl(i.getImageUrl());
            ie.setPrimary(i.isPrimary());
            ie.setDisplayOrder(i.getDisplayOrder());
            ie.setProduct(e);
            imageEntities.add(ie);
        }

        e.setVariants(variantEntities);
        e.setImages(imageEntities);

        return e;
    }
}