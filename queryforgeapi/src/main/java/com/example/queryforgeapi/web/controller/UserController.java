package com.example.queryforgeapi.web.controller;

import com.example.queryforgeapi.persistence.entity.UserEntity;
import com.example.queryforgeapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        UserEntity user = this.userService.getById(id);
        if (user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserEntity> add(@RequestBody UserEntity user) {
        UserEntity aux = this.userService.save(user);
        if (aux != null) {
            return ResponseEntity.ok(aux);
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updateLastLogin(@PathVariable int id) {
        if (this.userService.updateLastLogin(id)) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return this.userService.delete(id) ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }
}
