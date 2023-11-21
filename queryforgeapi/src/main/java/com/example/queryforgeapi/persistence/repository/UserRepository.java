package com.example.queryforgeapi.persistence.repository;

import com.example.queryforgeapi.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    public Optional<UserEntity> findFirstByUsername(String name);
}
