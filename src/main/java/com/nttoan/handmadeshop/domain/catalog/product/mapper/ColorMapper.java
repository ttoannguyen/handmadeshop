package com.nttoan.handmadeshop.domain.catalog.product.mapper;

import org.mapstruct.Mapper;

import com.nttoan.handmadeshop.domain.catalog.product.dto.response.ColorResponse;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ColorEntity;

@Mapper(componentModel = "spring")
public interface ColorMapper {
    ColorResponse toColorResponse(ColorEntity entity);
}
