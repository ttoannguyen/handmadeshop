package com.nttoan.handmadeshop.domain.identity.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttoan.handmadeshop.domain.common.dto.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestToken {

    @GetMapping
    public BaseResponse<String> testToken() {
        return BaseResponse.of("Token is valid");
    }
}
