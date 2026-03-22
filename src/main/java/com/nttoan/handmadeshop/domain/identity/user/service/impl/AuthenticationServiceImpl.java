package com.nttoan.handmadeshop.domain.identity.user.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nttoan.handmadeshop.application.exception.InvalidTokenException;
import com.nttoan.handmadeshop.domain.identity.security.CustomUserDetails;
import com.nttoan.handmadeshop.domain.identity.security.JwtService;
import com.nttoan.handmadeshop.domain.identity.user.dto.request.LoginRequest;
import com.nttoan.handmadeshop.domain.identity.user.dto.request.RegisterRequest;
import com.nttoan.handmadeshop.domain.identity.user.dto.response.LoginResponse;
import com.nttoan.handmadeshop.domain.identity.user.dto.response.RefreshTokenResponse;
import com.nttoan.handmadeshop.domain.identity.user.dto.response.RegisterResponse;
import com.nttoan.handmadeshop.domain.identity.user.entity.Role;
import com.nttoan.handmadeshop.domain.identity.user.entity.UserEntity;
import com.nttoan.handmadeshop.domain.identity.user.mapper.UserMapper;
import com.nttoan.handmadeshop.domain.identity.user.repository.UserRepository;
import com.nttoan.handmadeshop.domain.identity.user.service.AuthenticationService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        UserEntity user = new UserEntity();

        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setFullname(registerRequest.getFullname());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        user.setDateOfBirth(registerRequest.getDateOfBirth());
        user.setRole(Role.USER);
        user.setEnabled(true);

        userRepository.save(user);
        return userMapper.toRegisterResponse(user);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest, HttpServletResponse servletResponse) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserEntity user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String accessToken = jwtService.generateAccessToken(CustomUserDetails.from(user));

        String refreshToken = jwtService.generateRefreshToken(CustomUserDetails.from(user));

        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);

        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setAttribute("SameSite", "Site");
        ;
        refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60);

        servletResponse.addCookie(refreshTokenCookie);

        return LoginResponse.builder().accessToken(accessToken).build();
    }

    @Override
    public RefreshTokenResponse refreshToken(String refreshToken) throws InvalidTokenException {
        if (jwtService.isTokenValid(refreshToken)) {
            String username = jwtService.extractUsername(refreshToken);
            UserEntity user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            String accessToken = jwtService.generateAccessToken(CustomUserDetails.from(user));
            return RefreshTokenResponse.builder().accessToken(accessToken).build();
        } else {
            throw new InvalidTokenException("ERROR", "Invalid refresh token");
        }

    }

}
