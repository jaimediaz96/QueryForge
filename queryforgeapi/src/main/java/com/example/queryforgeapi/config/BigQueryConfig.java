package com.example.queryforgeapi.config;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;

public class BigQueryConfig {

    @Value("${BIGQUERY_CREDENTIALS}")
    private String credentialsFilePath;

    @Bean
    public BigQuery bigQuery() throws IOException {
        return BigQueryOptions.newBuilder()
                .setCredentials(credentials())
                .build()
                .getService();
    }

    private Credentials credentials() throws IOException {
        try (InputStream is = getClass().getResourceAsStream(credentialsFilePath)) {
            if (is == null) throw new Exception("NO Credencials");
            return GoogleCredentials.fromStream(is);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
