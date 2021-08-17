package com.heroku.match;

import javax.sql.DataSource;

import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.sql.postgres.PostgresStorageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MatchApplication {

	@Autowired
	DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(MatchApplication.class, args);
	}
	
	
	@Bean
    public StorageProvider storageProvider(JobMapper jobMapper) {
		
		PostgresStorageProvider storageProvider = new PostgresStorageProvider(dataSource);
        storageProvider.setJobMapper(jobMapper);
        return storageProvider;
    }

}
