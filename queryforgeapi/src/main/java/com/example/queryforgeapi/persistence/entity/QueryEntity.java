package com.example.queryforgeapi.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "queries")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class QueryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "query_id")
    private Integer queryId;

    @Column(name = "user_id")
    private Integer userId;

    private String name;

    @Column(name = "query_term")
    private String queryTerm;

    @Column(name = "query_table_name")
    private String queryTableName;

    @Column(name = "query_region_name")
    private String queryRegionName;

    @Column(name = "query_country_name")
    private String queryCountryName;

    @Column(name = "query_start_date")
    private LocalDate queryStartDate;

    @Column(name = "query_end_date")
    private LocalDate queryEndDate;

    private String comment;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
