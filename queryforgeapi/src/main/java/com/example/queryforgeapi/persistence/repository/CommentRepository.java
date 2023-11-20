package com.example.queryforgeapi.persistence.repository;

import com.example.queryforgeapi.persistence.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {
}
