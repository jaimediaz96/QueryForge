package com.example.queryforgeapi.persistence.repository;

import com.example.queryforgeapi.persistence.entity.QueryEntity;
import org.springframework.data.repository.CrudRepository;

public interface QueryRepository extends CrudRepository<QueryEntity, Integer> {
}
