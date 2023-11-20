package com.example.queryforgeapi.persistence.repository;

import com.example.queryforgeapi.persistence.entity.CommentEntity;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface CommentPageSortRepository extends ListPagingAndSortingRepository<CommentEntity, Integer> {
}
