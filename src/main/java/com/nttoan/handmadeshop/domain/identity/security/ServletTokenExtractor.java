package com.nttoan.handmadeshop.domain.identity.security;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ServletTokenExtractor {
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    public String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH_HEADER);
        if (!StringUtils.hasText(authHeader)) {
            return "";
        }

        if (!authHeader.startsWith(BEARER_PREFIX)) {
            return "";
        }
        String token = authHeader.substring(BEARER_PREFIX.length());
        return StringUtils.hasText(token) ? token : "";
    }
}
