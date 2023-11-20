package com.example.queryforgeapi.persistence.repository;

import com.example.queryforgeapi.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
