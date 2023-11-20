package com.example.queryforgeapi.service;

import com.example.queryforgeapi.persistence.entity.UserEntity;
import com.example.queryforgeapi.persistence.repository.UserPageSortRepository;
import com.example.queryforgeapi.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserPageSortRepository userPageSortRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserPageSortRepository userPageSortRepository) {
        this.userRepository = userRepository;
        this.userPageSortRepository = userPageSortRepository;
    }

    public Page<UserEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.userPageSortRepository.findAll(pageRequest);
    }

    public UserEntity getById(int id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public UserEntity save(UserEntity user) {
        return this.userRepository.save(user);
    }

    public Boolean delete(int id) {
        if(exists(id)) {
            this.userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean exists(int id) {
        return this.userRepository.existsById(id);
    }
}
