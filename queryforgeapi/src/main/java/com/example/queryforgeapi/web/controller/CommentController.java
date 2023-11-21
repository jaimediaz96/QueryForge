package com.example.queryforgeapi.web.controller;

import com.example.queryforgeapi.persistence.entity.CommentEntity;
import com.example.queryforgeapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<Page<CommentEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int elements,
                                                      @RequestParam(defaultValue = "userId") String sortBy,
                                                      @RequestParam(defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(this.commentService.getAll(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentEntity> getById(@PathVariable int id) {
        CommentEntity comment = this.commentService.getById(id);
        return comment != null ? ResponseEntity.ok(comment) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<CommentEntity>> getAllByUserId(@PathVariable int userId,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int elements,
                                                              @RequestParam(defaultValue = "userId") String sortBy,
                                                              @RequestParam(defaultValue = "ASC") String sortDirection) {
        Page<CommentEntity> comments = this.commentService.getAllByUserId(userId, page, elements, sortBy, sortDirection);
        return comments != null ? ResponseEntity.ok(comments) : ResponseEntity.notFound().build();
    }

    @GetMapping("/query/{queryId}")
    public ResponseEntity<Page<CommentEntity>> getAllByQueryId(@PathVariable int queryId,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int elements,
                                                              @RequestParam(defaultValue = "userId") String sortBy,
                                                              @RequestParam(defaultValue = "ASC") String sortDirection) {
        Page<CommentEntity> comments = this.commentService.getAllByQueryId(queryId, page, elements, sortBy, sortDirection);
        return comments != null ? ResponseEntity.ok(comments) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CommentEntity> add(@RequestBody CommentEntity comment) {
        CommentEntity newComment = this.commentService.save(comment);
        return newComment != null ? ResponseEntity.ok(newComment) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return this.commentService.delete(id) ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }
}
