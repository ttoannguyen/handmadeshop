package com.nttoan.handmadeshop.domain.catalog.product.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nttoan.handmadeshop.domain.catalog.product.dto.response.*;
import com.nttoan.handmadeshop.domain.catalog.product.entity.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // ===================== PRODUCT =====================

    @Mapping(target = "categories", source = "productCategories")
    @Mapping(target = "images", source = "productImages")
    ProductResponse toResponse(ProductEntity entity);

    List<ProductResponse> toResponseList(List<ProductEntity> entities);

    // ===================== CATEGORY =====================

    @Mapping(target = "id", source = "category.id")
    @Mapping(target = "name", source = "category.name")
    @Mapping(target = "slug", source = "category.slug")
    CategorySimpleResponse toCategory(ProductCategoryEntity entity);

    List<CategorySimpleResponse> toCategoryList(List<ProductCategoryEntity> entities);

    // ===================== VARIANT =====================

    @Mapping(target = "colorId", source = "color.id")
    @Mapping(target = "colorName", source = "color.name")
    @Mapping(target = "hexCode", source = "color.hexCode")
    ProductVariantResponse toVariant(ProductVariantEntity entity);

    List<ProductVariantResponse> toVariantList(List<ProductVariantEntity> entities);

    // ===================== IMAGE =====================

    ProductImageResponse toImage(ProductImageEntity entity);

    List<ProductImageResponse> toImageList(List<ProductImageEntity> entities);
}