package com.example.queryforgeapi.service;

import com.example.queryforgeapi.persistence.entity.QueryEntity;
import com.example.queryforgeapi.persistence.repository.QueryPageSortRepository;
import com.example.queryforgeapi.persistence.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QueryService {
    private final QueryRepository queryRepository;
    private final QueryPageSortRepository queryPageSortRepository;

    @Autowired
    public QueryService(QueryRepository queryRepository, QueryPageSortRepository queryPageSortRepository) {
        this.queryRepository = queryRepository;
        this.queryPageSortRepository = queryPageSortRepository;
    }


    public Page<QueryEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.queryPageSortRepository.findAll(pageRequest);
    }

    public QueryEntity getById(int id) {
        return this.queryRepository.findById(id).orElse(null);
    }

    public QueryEntity save(QueryEntity query) {
        return this.queryRepository.save(query);
    }

    public Boolean delete(int id) {
        if(exists(id)) {
            this.queryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean exists(int id) {
        return this.queryRepository.existsById(id);
    }
}
