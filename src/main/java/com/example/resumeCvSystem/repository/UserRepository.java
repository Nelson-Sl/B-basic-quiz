package com.example.resumeCvSystem.repository;

import com.example.resumeCvSystem.domain.User;
import com.example.resumeCvSystem.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findById(Long id);
}

