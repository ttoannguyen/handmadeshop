package com.nttoan.handmadeshop.domain.identity.user.dto.response;

import java.time.LocalDate;
import java.util.UUID;

import com.nttoan.handmadeshop.domain.identity.user.entity.Role;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private UUID id;
    private String fullname;
    private String username;
    private String email;
    private LocalDate dateOfBirth;
    private Role role;
    private boolean enabled;
}
