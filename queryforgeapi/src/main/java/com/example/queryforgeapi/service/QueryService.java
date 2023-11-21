package com.example.queryforgeapi.service;

import com.example.queryforgeapi.persistence.entity.QueryEntity;
import com.example.queryforgeapi.persistence.repository.QueryPageSortRepository;
import com.example.queryforgeapi.persistence.repository.QueryRepository;
import com.example.queryforgeapi.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class QueryService {
    private final QueryRepository queryRepository;
    private final QueryPageSortRepository queryPageSortRepository;
    private final UserRepository userRepository;

    @Autowired
    public QueryService(QueryRepository queryRepository, QueryPageSortRepository queryPageSortRepository, UserRepository userRepository) {
        this.queryRepository = queryRepository;
        this.queryPageSortRepository = queryPageSortRepository;
        this.userRepository = userRepository;
    }

    public Page<QueryEntity> getAll(int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.queryPageSortRepository.findAll(pageRequest);
    }

    public QueryEntity getById(int id) {
        Optional<QueryEntity> query = this.queryRepository.findById(id);
        if (query.isPresent()) return query.orElse(null);
        return null;
    }

    public Page<QueryEntity> getAllByUserId(int userId, int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        if (this.userRepository.findById(userId).isEmpty()) return null;
        return this.queryPageSortRepository.findAllByUserId(pageRequest, userId);
    }

    public QueryEntity save(QueryEntity query) {
        if (query.getUserId() == null) return null;
        if (this.userRepository.findById(query.getUserId()).isEmpty()) return null;
        if (query.getName() == null) return null;
        if (query.getName().isEmpty()) return null;
        if (query.getQueryTerm() == null) return null;
        if (query.getQueryTerm().isEmpty()) return null;
        if (query.getQueryTableName() == null || query.getQueryTableName().isEmpty() ||
            query.getQueryTableName().equalsIgnoreCase("top")) query.setQueryTableName("top");
        else query.setQueryTableName("rising");
        query.setCreatedAt(LocalDateTime.now());
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
