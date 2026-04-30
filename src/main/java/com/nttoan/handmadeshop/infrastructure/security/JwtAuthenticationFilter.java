package com.nttoan.handmadeshop.infrastructure.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
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
    // private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = tokenExtractor.extrectToken(request);

        if (token != null && jwtService.isValid(token)) {
            Claims claims = jwtService.extractAllClaims(token);
            
            String userId = claims.getSubject();
            String role = claims.get("role", String.class);

            var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

            var auth = new UsernamePasswordAuthenticationToken(userId, null, authorities);
            
            SecurityContextHolder.getContext().setAuthentication(auth);
            // String userId = jwtService.extractUserId(token);
            // var userOpt = userRepository.findById(userId);

            // if (userOpt.isPresent()) {
            //     User user = userOpt.get();
            //     var auth = new UsernamePasswordAuthenticationToken(user.getEmail(), null, List.of());
            //     SecurityContextHolder.getContext().setAuthentication(auth);
            // }
        }
        filterChain.doFilter(request, response);
    }

}
