package com.kips.product.api.common.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final JdbcTemplate jdbcTemplate;

    private final ResourceLoader resourceLoader;

    @Value("${data.seed.enabled:false}")
    private boolean isSeeded;

    @PostConstruct
    public void seedDatabase() {
        if (isSeeded) {
            List<String> sqlFiles = List.of("categories-part-1", "categories-part-2", "categories-part-3",
                    "categories-part-4", "brands", "products","product_attributes");
            sqlFiles.forEach(this::executeSql);
        }
    }

    private void executeSql(String fileName) {
        try {
            Resource resource = resourceLoader.getResource(String.format("classpath:%s.sql", fileName));
            String sql = new String(Files.readAllBytes(resource.getFile().toPath()));
            jdbcTemplate.execute(sql);
            log.info("Successfully executed SQL file: {}.sql", fileName);
        } catch (IOException e) {
            log.error("Failed to read SQL file: {}.sql", fileName, e);
        }
    }
}
