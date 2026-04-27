package com.nttoan.handmadeshop.infrastructure.persistence.jpa.entity;

import java.time.LocalDate;
import com.nttoan.handmadeshop.domain.identity.user.entity.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserJpaEntity extends BaseJpaEntity {
    private String username;
    private String email;
    private String passwordHash;
    private String fullName;
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean enabled;
}
