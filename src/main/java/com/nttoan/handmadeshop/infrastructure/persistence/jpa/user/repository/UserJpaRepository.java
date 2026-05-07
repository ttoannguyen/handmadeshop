package com.nttoan.handmadeshop.infrastructure.persistence.jpa.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttoan.handmadeshop.infrastructure.persistence.jpa.user.entity.UserJpaEntity;


public interface UserJpaRepository extends JpaRepository<UserJpaEntity, String>{
    Optional<UserJpaEntity> findByEmail(String email);

    Optional<UserJpaEntity> findByUsername(String username);
}
