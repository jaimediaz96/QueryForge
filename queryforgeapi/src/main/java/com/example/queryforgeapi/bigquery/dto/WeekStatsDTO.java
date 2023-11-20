package com.example.queryforgeapi.bigquery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeekStatsDTO {
    private LocalDate week;
    private Double avgScore;
    private Double avgRank;
}
