package com.nttoan.handmadeshop.domain.identity.user.entity;

import java.time.LocalDate;

import com.nttoan.handmadeshop.domain.common.entity.BaseEntity;

public class User extends BaseEntity {
    private String username;
    private String email;
    private String passwordHash;
    private String fullName;
    private LocalDate dateOfBirth;
    private Role role;
    private boolean enabled;

    public User(String username, String email, String passwordHash, String fullName, LocalDate dateOfBirth, Role role) {
        super();
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.enabled = true; // Default to enabled
    }

    public static User restore(
            String id,
            String username,
            String email,
            String passwordHash,
            String fullName,
            LocalDate dateOfBirth,
            Role role,
            boolean enabled) {
        User user = new User(
                username,
                email,
                passwordHash,
                fullName,
                dateOfBirth,
                role);
        user.setId(id); // BaseEntity (protected)
        user.enabled = enabled;
        return user;
    }

    public void changeEmail(String newEmail) {
        if (newEmail == null || !newEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = newEmail;
    }

    public void changePassword(String newPasswordHash) {
        if (newPasswordHash == null || newPasswordHash.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        this.passwordHash = newPasswordHash;
    }

    public void changeFullName(String newFullName) {
        if (newFullName == null || newFullName.isBlank()) {
            throw new IllegalArgumentException("Full name cannot be empty");
        }
        this.fullName = newFullName;
    }

    public void changeDateOfBirth(LocalDate newDateOfBirth) {
        this.dateOfBirth = newDateOfBirth;
    }

    public void changeRole(Role newRole) {
        if (newRole == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        this.role = newRole;
    }

    public void activate() {
        this.enabled = true;
    }

    public void deactivate() {
        this.enabled = false;
    }

    // ===== GETTERS =====

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Role getRole() {
        return role;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
