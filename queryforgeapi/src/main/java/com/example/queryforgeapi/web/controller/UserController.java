package com.example.queryforgeapi.web.controller;

import com.example.queryforgeapi.persistence.entity.UserEntity;
import com.example.queryforgeapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int elements) {
        return ResponseEntity.ok(this.userService.getAll(page, elements));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable int id) {
        return ResponseEntity.ok(this.userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<UserEntity> add(@RequestBody UserEntity user) {
        if (user.getUserId() == null || !this.userService.exists(user.getUserId())){
            user.setCreatedAt(LocalDateTime.now());
            return ResponseEntity.ok(this.userService.save(user));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return this.userService.delete(id) ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }
}
