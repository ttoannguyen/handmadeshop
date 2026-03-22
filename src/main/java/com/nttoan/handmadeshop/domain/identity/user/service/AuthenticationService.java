package com.nttoan.handmadeshop.domain.identity.user.service;

import com.nttoan.handmadeshop.application.exception.InvalidTokenException;
import com.nttoan.handmadeshop.domain.identity.user.dto.request.LoginRequest;
import com.nttoan.handmadeshop.domain.identity.user.dto.request.RegisterRequest;
import com.nttoan.handmadeshop.domain.identity.user.dto.response.LoginResponse;
import com.nttoan.handmadeshop.domain.identity.user.dto.response.RefreshTokenResponse;
import com.nttoan.handmadeshop.domain.identity.user.dto.response.RegisterResponse;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    public RegisterResponse register(RegisterRequest registerRequest);

    public LoginResponse login(LoginRequest loginRequest, HttpServletResponse servletResponse);

    public RefreshTokenResponse refreshToken(String refreshToken) throws InvalidTokenException;
}
