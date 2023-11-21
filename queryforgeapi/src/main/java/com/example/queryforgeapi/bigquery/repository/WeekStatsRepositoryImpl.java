package com.example.queryforgeapi.bigquery.repository;

import com.example.queryforgeapi.bigquery.dto.WeekStatsDTO;
import com.google.cloud.bigquery.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WeekStatsRepositoryImpl implements WeekStatsRepository {
    private final BigQuery bigQuery;

    @Autowired
    public WeekStatsRepositoryImpl(BigQuery bigQuery) {
        this.bigQuery = bigQuery;
    }

    @Override
    public List<WeekStatsDTO> findWeekStats(String tableName, String regionName, String countryName, String term,
                                           LocalDate startDate, LocalDate endDate) {
        String query = "SELECT week, AVG(score) AS avg_score, AVG(rank) AS avg_rank " +
                "FROM `" + tableName + "` " +
                "WHERE " + (regionName != null ? "region_name = '" + regionName + "'" : "1=1") +
                " AND " + (countryName != null ? "country_name = '" + countryName + "'" : "1=1") +
                " AND " + (term != null ? "term = '" + term + "'" : "1=1") +
                " AND " + (startDate != null ? "week >= '" + startDate.toString() + "'" : "1=1") +
                " AND " + (endDate != null ? "week <= '" + endDate.toString() + "' " : "1=1 ") +
                "GROUP BY week " +
                "ORDER BY week";
        return executeQuery(query);
    }

    private List<WeekStatsDTO> executeQuery(String query) {
        try {
            QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();

            Job queryJob = bigQuery.create(JobInfo.newBuilder(queryConfig).build());
            queryJob.waitFor();

            TableResult result = queryJob.getQueryResults();

            List<WeekStatsDTO> weekStatsList = new ArrayList<>();

            result.iterateAll().forEach(row -> {
                WeekStatsDTO weekStatsDTO = new WeekStatsDTO();
                LocalDate date = LocalDate.parse(row.get("week").getStringValue());
                weekStatsDTO.setWeek(date);
                weekStatsDTO.setAvgScore(row.get("avg_score").isNull() ? 0.0 : row.get("avg_score").getDoubleValue());
                weekStatsDTO.setAvgRank(row.get("avg_rank").isNull() ? 0.0 : row.get("avg_rank").getDoubleValue());

                weekStatsList.add(weekStatsDTO);
            });

            return weekStatsList;
        } catch (BigQueryException | InterruptedException e) {
            System.out.println("ERROR: " + e.getLocalizedMessage());
            return null;
        }
    }
}
