package com.nttoan.handmadeshop.infrastructure.persistence.jpa.repositoty;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nttoan.handmadeshop.domain.identity.user.entity.User;
import com.nttoan.handmadeshop.domain.identity.user.repository.UserRepository;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.entity.UserJpaEntity;
import com.nttoan.handmadeshop.infrastructure.persistence.jpa.mapper.UserMapper;

@Repository
public class UserJpaRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserJpaRepositoryImpl(
            UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = UserMapper.toJpa(user);
        UserJpaEntity saved = jpaRepository.save(entity);
        return UserMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findById(String id) {
        return jpaRepository.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
    return jpaRepository.findByEmail(email).map(UserMapper::toDomain);
    }

}
