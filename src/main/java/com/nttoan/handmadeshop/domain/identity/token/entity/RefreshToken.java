package com.nttoan.handmadeshop.domain.identity.token.entity;

import java.time.Instant;

public class RefreshToken {
    @SuppressWarnings("unused")
    private String id;
    private String userId;
    private String token;
    private Instant expiry;
    private boolean revoked;

    public RefreshToken(String id, String userId, String token, Instant expiry) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.expiry = expiry;
        this.revoked = false;
    }

    public void revoke() {
        this.revoked = true;
    }

    public boolean isValid() {
        return !revoked && expiry.isAfter(Instant.now());
    }

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }

}
