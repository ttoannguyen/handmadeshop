package com.nttoan.handmadeshop.domain.identity.user.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.domain.common.dto.BaseResponse;
import com.nttoan.handmadeshop.domain.identity.user.dto.request.LoginRequest;
import com.nttoan.handmadeshop.domain.identity.user.dto.request.RegisterRequest;
import com.nttoan.handmadeshop.domain.identity.user.dto.response.LoginResponse;
import com.nttoan.handmadeshop.domain.identity.user.dto.response.RefreshTokenResponse;
import com.nttoan.handmadeshop.domain.identity.user.dto.response.RegisterResponse;
import com.nttoan.handmadeshop.domain.identity.user.service.AuthenticationService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public BaseResponse<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return BaseResponse.of(authenticationService.register(request));
    }

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginRequest request,
            HttpServletResponse httpServletResponse) {
        return BaseResponse.of(authenticationService.login(request, httpServletResponse));
    }

    @PostMapping("/refresh-token")
    @SneakyThrows
    public BaseResponse<RefreshTokenResponse> refreshToken(
            @CookieValue("refreshToken") String refreshToken) {

        return BaseResponse.of(authenticationService.refreshToken(refreshToken));
    }
}
