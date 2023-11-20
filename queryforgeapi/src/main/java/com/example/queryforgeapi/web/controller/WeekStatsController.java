package com.example.queryforgeapi.web.controller;

import com.example.queryforgeapi.bigquery.dto.WeekStatsDTO;
import com.example.queryforgeapi.service.WeekStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bigquery")
public class WeekStatsController {
    private final WeekStatsService weekStatsService;

    @Autowired
    public WeekStatsController(WeekStatsService weekStatsService) {
        this.weekStatsService = weekStatsService;
    }

    @GetMapping
    public ResponseEntity<List<WeekStatsDTO>> getWeekStats(@RequestParam String term,
                                                           @RequestParam(required = false, defaultValue = "top") String tableName,
                                                           @RequestParam(required = false) String regionName,
                                                           @RequestParam(required = false) String countryName,
                                                           @RequestParam(required = false) String startDate,
                                                           @RequestParam(required = false) String endDate) {
        LocalDate start = startDate != null ? LocalDate.parse(startDate) : null;
        LocalDate end = endDate != null ? LocalDate.parse(endDate) : LocalDate.now();
        return ResponseEntity.ok(this.weekStatsService.getWeekStats(tableName, regionName, countryName, term, start, end));
    }
}
