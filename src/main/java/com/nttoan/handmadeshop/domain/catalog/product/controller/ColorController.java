package com.nttoan.handmadeshop.domain.catalog.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.domain.catalog.product.dto.request.ColorRequest;
import com.nttoan.handmadeshop.domain.catalog.product.dto.response.ColorResponse;
import com.nttoan.handmadeshop.domain.catalog.product.service.ColorService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/colors")
@RequiredArgsConstructor
public class ColorController {
    private final ColorService colorService;

    @PostMapping()
    public ColorResponse createColor(@RequestBody ColorRequest request) {
        return colorService.create(request);
    }

}
