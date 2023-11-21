package com.example.queryforgeapi.service;

import com.example.queryforgeapi.bigquery.dto.WeekStatsDTO;
import com.example.queryforgeapi.bigquery.repository.WeekStatsRepository;
import com.example.queryforgeapi.persistence.entity.QueryEntity;
import com.example.queryforgeapi.persistence.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Service
public class WeekStatsService {
    @Value("${BIGQUERY_DB_TABLE_TOP}")
    private String TOP;

    @Value("${BIGQUERY_DB_TABLE_RISING}")
    private String RISING;
    private final WeekStatsRepository weekStatsRepository;
    private final QueryRepository queryRepository;

    @Autowired
    public WeekStatsService(WeekStatsRepository weekStatsRepository, QueryRepository queryRepository) {
        this.weekStatsRepository = weekStatsRepository;
        this.queryRepository = queryRepository;
    }

    public List<WeekStatsDTO> getWeekStats(String tableName, String regionName, String countryName, String term,
                                           String startDate, String endDate) {
        if (term == null) return null;
        String newTableName;
        if (tableName == null || tableName.equalsIgnoreCase("top")) newTableName = TOP;
        else newTableName = RISING;
        LocalDate newStartDate = startDate != null ? LocalDate.parse(startDate) : null;
        LocalDate newEndDate = endDate != null ? LocalDate.parse(endDate) : LocalDate.now();
        return this.weekStatsRepository.findWeekStats(newTableName, regionName, countryName, term, newStartDate, newEndDate);
    }

    public List<WeekStatsDTO> getWeekStatsByQueryId(@PathVariable int queryId) {
        QueryEntity query = this.queryRepository.findById(queryId).orElse(null);
        if (query == null) return null;
        String newTableName;
        if (query.getQueryTableName() == null || query.getQueryTableName().equalsIgnoreCase("top")) newTableName = TOP;
        else newTableName = RISING;
        return this.weekStatsRepository.findWeekStats(newTableName, query.getQueryRegionName(),
                query.getQueryCountryName(), query.getQueryTerm(), query.getQueryStartDate(), query.getQueryEndDate());
    }
}
