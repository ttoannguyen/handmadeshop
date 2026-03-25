package com.nttoan.handmadeshop.domain.catalog.product.service;

import com.nttoan.handmadeshop.domain.catalog.product.dto.request.ColorRequest;
import com.nttoan.handmadeshop.domain.catalog.product.dto.response.ColorResponse;

public interface ColorService {
    ColorResponse create(ColorRequest request);
}
