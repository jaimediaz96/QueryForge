package com.example.queryforgeapi.service;

import com.example.queryforgeapi.persistence.entity.CommentEntity;
import com.example.queryforgeapi.persistence.repository.CommentPageSortRepository;
import com.example.queryforgeapi.persistence.repository.CommentRepository;
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
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentPageSortRepository commentPageSortRepository;
    private final UserRepository userRepository;
    private final QueryRepository queryRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentPageSortRepository commentPageSortRepository,
                          UserRepository userRepository, QueryRepository queryRepository) {
        this.commentRepository = commentRepository;
        this.commentPageSortRepository = commentPageSortRepository;
        this.userRepository = userRepository;
        this.queryRepository = queryRepository;
    }


    public Page<CommentEntity> getAll(int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.commentPageSortRepository.findAll(pageRequest);
    }

    public CommentEntity getById(int id) {
        Optional<CommentEntity> comment = this.commentRepository.findById(id);
        return comment.isPresent() ? comment.orElse(null) : null;
    }

    public Page<CommentEntity> getAllByUserId(int userId, int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        if (this.userRepository.findById(userId).isEmpty()) return null;
        return this.commentPageSortRepository.findAllByUserId(pageRequest, userId);
    }

    public Page<CommentEntity> getAllByQueryId(int queryId, int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        if (this.queryRepository.findById(queryId).isEmpty()) return null;
        return this.commentPageSortRepository.findAllByQueryId(pageRequest, queryId);
    }

    public CommentEntity save(CommentEntity comment) {
        if (comment.getQueryId() == null) return null;
        if (!this.queryRepository.existsById(comment.getQueryId())) return null;
        if (comment.getUserId() == null) return null;
        if (!this.userRepository.existsById(comment.getUserId())) return null;
        if (comment.getCommentText() == null)  return null;
        if (comment.getCommentText().isEmpty()) return null;
        comment.setCreatedAt(LocalDateTime.now());
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
