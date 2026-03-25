package com.nttoan.handmadeshop.domain.catalog.product.service.impl;

import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.domain.catalog.product.dto.request.ColorRequest;
import com.nttoan.handmadeshop.domain.catalog.product.dto.response.ColorResponse;
import com.nttoan.handmadeshop.domain.catalog.product.entity.ColorEntity;
import com.nttoan.handmadeshop.domain.catalog.product.mapper.ColorMapper;
import com.nttoan.handmadeshop.domain.catalog.product.repository.ColorRepository;
import com.nttoan.handmadeshop.domain.catalog.product.service.ColorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;

    @Override
    public ColorResponse create(ColorRequest request) {
        if (colorRepository.existsByName(request.getName()))
            throw new RuntimeException("Color already exists");

        ColorEntity colorEntity = ColorEntity.builder()
                .name(request.getName())
                .hexCode(request.getHexCode())
                .build();

        return colorMapper.toColorResponse(colorRepository.save(colorEntity));

    }

}
