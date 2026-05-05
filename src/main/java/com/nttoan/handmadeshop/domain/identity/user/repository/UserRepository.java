package com.nttoan.handmadeshop.domain.identity.user.repository;

import java.util.Optional;

import com.nttoan.handmadeshop.domain.identity.user.entity.User;

public interface UserRepository {
    User save(User user);

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username); 

}
