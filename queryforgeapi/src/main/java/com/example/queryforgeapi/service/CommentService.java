package com.example.queryforgeapi.service;

import com.example.queryforgeapi.persistence.entity.CommentEntity;
import com.example.queryforgeapi.persistence.repository.CommentPageSortRepository;
import com.example.queryforgeapi.persistence.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentPageSortRepository commentPageSortRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentPageSortRepository commentPageSortRepository) {
        this.commentRepository = commentRepository;
        this.commentPageSortRepository = commentPageSortRepository;
    }


    public Page<CommentEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.commentPageSortRepository.findAll(pageRequest);
    }

    public CommentEntity getById(int id) {
        return this.commentRepository.findById(id).orElse(null);
    }

    public CommentEntity save(CommentEntity comment) {
        return this.commentRepository.save(comment);
    }

    public Boolean delete(int id) {
        if(exists(id)) {
            this.commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean exists(int id) {
        return this.commentRepository.existsById(id);
    }
}
