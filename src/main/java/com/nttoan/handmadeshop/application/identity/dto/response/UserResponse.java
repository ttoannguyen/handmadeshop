package com.nttoan.handmadeshop.application.identity.dto.response;

import lombok.Builder;

@Builder
public class UserResponse {
    public String id;
    public String username;
    public String email;
    public String fullName;
}