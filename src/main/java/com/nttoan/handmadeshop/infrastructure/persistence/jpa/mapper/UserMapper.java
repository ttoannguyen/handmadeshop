package com.nttoan.handmadeshop.infrastructure.persistence.jpa.mapper;

import com.nttoan.handmadeshop.domain.identity.user.entity.User;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.entity.UserJpaEntity;

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
                e.getRole(),
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
        e.setRole(u.getRole());
        e.setEnabled(u.isEnabled());

        return e;
    }
}
