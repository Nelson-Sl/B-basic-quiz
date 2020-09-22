package com.example.resumeCvSystem.repository;

import com.example.resumeCvSystem.collections.UserCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserCollection, Long> {
    Optional<UserCollection> findById(String id);
}

