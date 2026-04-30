package com.nttoan.handmadeshop.infrastructure.security;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ServletTokenExtractor {
    public String extrectToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Beader ")) {
            return header.substring(7);
        }
        return null;
    }
}
