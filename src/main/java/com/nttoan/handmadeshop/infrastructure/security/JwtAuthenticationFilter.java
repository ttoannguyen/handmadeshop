package com.nttoan.handmadeshop.infrastructure.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nttoan.handmadeshop.application.common.exception.AppException;
import com.nttoan.handmadeshop.application.common.exception.ErrorCode;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final ServletTokenExtractor tokenExtractor;
    private final UserDetailsService userDetailsService;
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String token = tokenExtractor.extractToken(request);
        log.info("RAW REQUEST URI: {}", request.getRequestURI());
        log.info("TOKEN = {}", token);
        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                String username = jwtService.extractUsername(token);
                log.info("Username: {}", username);
                if (!jwtService.isValid(token)) {
                    throw new AppException(ErrorCode.UNAUTHENTICATED);
                }

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception ex) {
                SecurityContextHolder.clearContext();
                // không throw ra để tránh break filter chain
                // (GlobalExceptionHandler sẽ xử lý nếu cần)
            }
        }

        filterChain.doFilter(request, response);
    }
}