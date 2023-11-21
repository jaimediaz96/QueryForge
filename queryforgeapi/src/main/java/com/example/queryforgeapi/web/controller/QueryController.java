package com.example.queryforgeapi.web.controller;

import com.example.queryforgeapi.persistence.entity.QueryEntity;
import com.example.queryforgeapi.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                                    @RequestParam(defaultValue = "10") int elements,
                                                    @RequestParam(defaultValue = "name") String sortBy,
                                                    @RequestParam(defaultValue = "ASC") String sortDirection) {
        return ResponseEntity.ok(this.queryService.getAll(page, elements, sortBy, sortDirection));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QueryEntity> getById(@PathVariable int id) {
        QueryEntity query = this.queryService.getById(id);
        return query != null ? ResponseEntity.ok(query) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<QueryEntity>> getAllByUserId(@PathVariable int userId,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int elements,
                                                            @RequestParam(defaultValue = "name") String sortBy,
                                                            @RequestParam(defaultValue = "ASC") String sortDirection) {
        Page<QueryEntity> queries = this.queryService.getAllByUserId(userId, page, elements, sortBy, sortDirection);
        return queries != null ? ResponseEntity.ok(queries) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<QueryEntity> add(@RequestBody QueryEntity query) {
        QueryEntity newQuery = this.queryService.save(query);
        return newQuery != null ? ResponseEntity.ok(newQuery) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return this.queryService.delete(id) ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }
}
