package com.example.queryforgeapi.persistence.repository;

import com.example.queryforgeapi.persistence.entity.QueryEntity;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface QueryPageSortRepository extends ListPagingAndSortingRepository<QueryEntity, Integer> {
}
