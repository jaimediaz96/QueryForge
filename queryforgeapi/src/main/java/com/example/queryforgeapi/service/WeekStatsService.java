package com.example.queryforgeapi.service;

import com.example.queryforgeapi.bigquery.dto.WeekStatsDTO;
import com.example.queryforgeapi.bigquery.repository.WeekStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WeekStatsService {
    private final WeekStatsRepository weekStatsRepository;

    @Autowired
    public WeekStatsService(WeekStatsRepository weekStatsRepository) {
        this.weekStatsRepository = weekStatsRepository;
    }

    public List<WeekStatsDTO> getWeekStats(String tableName, String regionName, String countryName, String term,
                                           LocalDate startDate, LocalDate endDate) {
        tableName = tableName.equalsIgnoreCase("top") ?
                "bigquery-public-data.google_trends.international_top_terms" :
                "bigquery-public-data.google_trends.international_top_rising_terms";
        return this.weekStatsRepository.findWeekStats(tableName, regionName, countryName, term, startDate, endDate);
    }
}
