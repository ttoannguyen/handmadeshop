package com.nttoan.handmadeshop.infrastructure.security;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

    private final JwtProperties props;
    private final SecretKey signingKey;

    public JwtService(JwtProperties props) {
        this.props = props;
        this.signingKey = buildSigningKey();
    }

    /**
     * Generate JWT token
     */
    public String generateToken(String username, String userId, String email, String role) {
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .subject(username) 
                .claim("userId", userId)
                .claim("email", email)
                .claim("role", role)
                .issuedAt(new Date(now))
                .expiration(new Date(now + props.getExpiration()))
                .signWith(signingKey)
                .compact();
    }

    /**
     * Extract username (subject)
     */
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Extract userId from claims
     */
    public String extractUserId(String token) {
        return extractAllClaims(token).get("userId", String.class);
    }

    /**
     * Validate token (signature + expiration)
     */
    public boolean isValid(String token) {
        try {
            Claims claims = parseClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extract all claims
     */
    public Claims extractAllClaims(String token) {
        return parseClaims(token);
    }

    /**
     * Centralized parsing logic
     */
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Build signing key (cached)
     */
    private SecretKey buildSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(props.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}