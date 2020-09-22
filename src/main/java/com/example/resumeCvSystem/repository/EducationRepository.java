package com.example.resumeCvSystem.repository;

import com.example.resumeCvSystem.entity.EducationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EducationRepository extends CrudRepository<EducationEntity, Long> {
    @Query(nativeQuery = true, value="select * from education where user_id = :userId")
    Optional<List<EducationEntity>> findAllByUser(@Param(value = "userId") Long userId);
}
