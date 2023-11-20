package com.example.queryforgeapi.web.controller;

import com.example.queryforgeapi.persistence.entity.QueryEntity;
import com.example.queryforgeapi.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/query")
public class QueryController {
    private final QueryService queryService;

    @Autowired
    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<Page<QueryEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int elements) {
        return ResponseEntity.ok(this.queryService.getAll(page, elements));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QueryEntity> getById(@PathVariable int id) {
        return ResponseEntity.ok(this.queryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<QueryEntity> add(@RequestBody QueryEntity query) {
        if (query.getQueryId() == null || !this.queryService.exists(query.getQueryId())){
            query.setCreatedAt(LocalDateTime.now());
            System.out.println(query);
            return ResponseEntity.ok(this.queryService.save(query));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return this.queryService.delete(id) ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }
}
