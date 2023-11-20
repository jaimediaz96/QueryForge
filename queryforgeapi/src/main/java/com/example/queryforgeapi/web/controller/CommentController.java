package com.example.queryforgeapi.web.controller;

import com.example.queryforgeapi.persistence.entity.CommentEntity;
import com.example.queryforgeapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
                                                      @RequestParam(defaultValue = "10") int elements) {
        return ResponseEntity.ok(this.commentService.getAll(page, elements));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentEntity> getById(@PathVariable int id) {
        return ResponseEntity.ok(this.commentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CommentEntity> add(@RequestBody CommentEntity comment) {
        if (comment.getCommentId() == null || !this.commentService.exists(comment.getCommentId())){
            comment.setCreatedAt(LocalDateTime.now());
            return ResponseEntity.ok(this.commentService.save(comment));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return this.commentService.delete(id) ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }
}
