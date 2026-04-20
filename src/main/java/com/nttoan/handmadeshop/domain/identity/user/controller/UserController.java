package com.nttoan.handmadeshop.domain.identity.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.domain.common.dto.BaseResponse;
import com.nttoan.handmadeshop.domain.identity.user.dto.response.ProfileResponse;
import com.nttoan.handmadeshop.domain.identity.user.service.ProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles")
public class UserController {
    private final ProfileService profileService;

    @GetMapping("/me")
    public BaseResponse<ProfileResponse> getProfile(Authentication authentication) {
        String username = authentication.getName();
        return BaseResponse.of(profileService.getProfile(username));
    }
}
