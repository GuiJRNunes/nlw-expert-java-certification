package com.github.guijrnunes.certification_nlw.seed;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CreateSeed {
    
    private final JdbcTemplate jdbcTemplate;

    public CreateSeed(DataSource dataSource) throws Exception {
        if (dataSource == null) {
            throw new Exception("Error creating CreateSeed object - null DataSource.");
        }
        
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // TODO : Create seed configuration file to choose a language
    public static void main(String[] args) {
        try {
            DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5434/pg_nlw", "admin", "admin");
            dataSource.setDriverClassName("org.postgresql.Driver");

            CreateSeed createSeed = new CreateSeed(dataSource);
            createSeed.run(args);
        } catch (Exception e) {
            System.err.println("Error while trying to establish a connection with the database: " + e.getMessage());
        }
    }

    private void run(String[] args) {
        executeSqlFile("src/main/resources/seed_scripts/enUS/create.sql");
    }

    private void executeSqlFile(String filePath) {
        try {
            String sqlScript = new String(Files.lines(Paths.get(filePath)).collect(Collectors.joining("\n")));
            
            jdbcTemplate.execute(sqlScript);

            System.out.println("SQL script executed with success [" + filePath + "]!");
        } catch (IOException e) {
            System.err.println("Error while trying to execute SQL script [" + filePath + "]:" + e.getMessage());
        }
    }
}
