package com.example.queryforgeapi.bigquery.repository;

import com.example.queryforgeapi.bigquery.dto.WeekStatsDTO;

import java.time.LocalDate;
import java.util.List;

public interface WeekStatsRepository {
    public List<WeekStatsDTO> findWeekStats(String tableName, String regionName, String countryName, String term,
                                           LocalDate startDate, LocalDate endDate);
}
