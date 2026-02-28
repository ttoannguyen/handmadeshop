package com.nttoan.handmadeshop.domain.identity.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final ServletTokenExtractor servletTokenExtractor;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    /**
     * To authenticate the request using a JWT token and set the user's identity
     * into the security context.
     * If this method does nothing -> the user is not authenticated.
     * We need to extract the token from the request.
     * We need to implement a method to extract the token from the request,
     * then we check the token's not null and valid.
     * We need to load the user details from the token, but we must have a
     * service to load the user details -> create an UserDetailsService
     * implementation to
     * load the user details from the database.
     * Then, we create an authentication with UsernamePasswordAuthenticationToken
     * and set it into the security holder context to make the user authenticated.
     * In conclusion, this filter authenticates request via JWT token and
     * populates the Security Context with User details to enable access to
     * protected resources such as controller, service, repository, etc.
     */
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        String token = this.servletTokenExtractor.extractToken(request);
        if (token != null
                && jwtService.isTokenValid(token)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails user = userDetailsService.loadUserByUsername(jwtService.extractUsername(token));
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
                    null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

}
