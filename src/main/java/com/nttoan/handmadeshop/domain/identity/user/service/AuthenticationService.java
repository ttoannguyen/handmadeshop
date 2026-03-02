package com.nttoan.handmadeshop.domain.identity.user.service;

import com.nttoan.handmadeshop.domain.common.dto.BaseResponse;
import com.nttoan.handmadeshop.domain.identity.user.dto.request.RegisterRequest;
import com.nttoan.handmadeshop.domain.identity.user.dto.response.RegisterResponse;

public interface AuthenticationService {
    public BaseResponse<RegisterResponse> register(RegisterRequest registerRequest);
}
