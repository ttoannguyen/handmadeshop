package com.nttoan.handmadeshop.infrastructure.persistence.jpa.repositoty;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttoan.handmadeshop.infrastructure.persistence.jpa.entity.UserJpaEntity;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, String>{
    Optional<UserJpaEntity> findByEmail(String email);
}
