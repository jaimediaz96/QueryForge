package com.example.queryforgeapi.persistence.repository;

import com.example.queryforgeapi.persistence.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface CommentPageSortRepository extends ListPagingAndSortingRepository<CommentEntity, Integer> {
    public Page<CommentEntity> findAllByUserId(Pageable pageable, int userId);
    public Page<CommentEntity> findAllByQueryId(Pageable pageable, int queryId);
}
