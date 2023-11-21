package com.example.queryforgeapi.service;

import com.example.queryforgeapi.persistence.entity.UserEntity;
import com.example.queryforgeapi.persistence.repository.UserPageSortRepository;
import com.example.queryforgeapi.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserPageSortRepository userPageSortRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserPageSortRepository userPageSortRepository) {
        this.userRepository = userRepository;
        this.userPageSortRepository = userPageSortRepository;
    }

    public Page<UserEntity> getAll(int page, int elements, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.userPageSortRepository.findAll(pageRequest);
    }

    public UserEntity getById(int id) {
        Optional<UserEntity> user = this.userRepository.findById(id);
        if (user.isPresent()) return user.orElse(null);
        return null;
    }

    public UserEntity getByUsername(String username) {
        Optional<UserEntity> user = this.userRepository.findFirstByUsername(username);
        if (user.isPresent()) return user.orElse(null);
        return null;
    }

    public UserEntity save(UserEntity user) {
        if (user.getUsername() == null) return null;
        if (user.getUsername().isEmpty()) return null;
        if (existsUsername(user.getUsername())) return null;
        user.setCreatedAt(LocalDateTime.now());
        return this.userRepository.save(user);
    }

    public Boolean updateLastLogin(int id) {
        if (exists(id)) {
            UserEntity user = this.userRepository.findById(id).orElse(new UserEntity());
            user.setLastLogin(LocalDateTime.now());
            this.userRepository.save(user);
            return true;
        }
        return false;
    }

    public Boolean delete(int id) {
        if(exists(id)) {
            this.userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean exists(int id) throws ResponseStatusException {
        return this.userRepository.existsById(id);
    }

    public boolean existsUsername(String name) {
        return this.userRepository.findFirstByUsername(name).isPresent();
    }
}
