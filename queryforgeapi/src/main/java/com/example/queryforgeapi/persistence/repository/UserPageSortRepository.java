package com.example.queryforgeapi.persistence.repository;

import com.example.queryforgeapi.persistence.entity.UserEntity;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface UserPageSortRepository extends ListPagingAndSortingRepository<UserEntity, Integer> {
}
