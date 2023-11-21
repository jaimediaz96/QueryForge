package com.example.queryforgeapi.web.controller;

import com.example.queryforgeapi.bigquery.dto.WeekStatsDTO;
import com.example.queryforgeapi.service.WeekStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<WeekStatsDTO> weekStatsDTOList = this.weekStatsService.getWeekStats(tableName, regionName, countryName,
                term, startDate, endDate);
        return weekStatsDTOList != null ? ResponseEntity.ok(weekStatsDTOList) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/query/{queryId}")
    public ResponseEntity<List<WeekStatsDTO>> getWeekStatsByQueryId(@PathVariable int queryId) {
        List<WeekStatsDTO> weekStatsDTOList = this.weekStatsService.getWeekStatsByQueryId(queryId);
        return weekStatsDTOList != null ? ResponseEntity.ok(weekStatsDTOList) : ResponseEntity.badRequest().build();
    }
}
