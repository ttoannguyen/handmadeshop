package com.nttoan.handmadeshop.infrastructure.persistence.jpa.user.mapper;

import com.nttoan.handmadeshop.domain.identity.user.entity.Role;
import com.nttoan.handmadeshop.domain.identity.user.entity.User;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.user.entity.UserJpaEntity;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.user.entity.UserRole;

public class UserMapper {
    public static User toDomain(UserJpaEntity e) {
        if (e == null)
            return null;

        return User.restore(
                e.getId(),
                e.getUsername(),
                e.getEmail(),
                e.getPasswordHash(),
                e.getFullName(),
                e.getDateOfBirth(),
                mapToDomainRole(e.getRole()),
                e.isEnabled());
    }

    public static UserJpaEntity toJpa(User u) {
        if (u == null)
            return null;

        UserJpaEntity e = new UserJpaEntity();

        e.setId(u.getId());
        e.setUsername(u.getUsername());
        e.setPasswordHash(u.getPasswordHash());
        e.setFullName(u.getFullName());
        e.setEmail(u.getEmail());
        e.setDateOfBirth(u.getDateOfBirth());
        e.setRole(mapToJpaRole(u.getRole()));
        e.setEnabled(u.isEnabled());

        return e;
    }

    private static Role mapToDomainRole(UserRole role) {
        if (role == null)
            return null;

        return switch (role) {
            case USER -> Role.USER;
            case ADMIN -> Role.ADMIN;
        };
    }

    private static UserRole mapToJpaRole(Role role) {
        if (role == null)
            return null;

        return switch (role) {
            case USER -> UserRole.USER;
            case ADMIN -> UserRole.ADMIN;
        };
    }
}
