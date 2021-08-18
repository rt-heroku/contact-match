package com.heroku.match.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jobrunr.jobs.Job;
import org.jobrunr.jobs.mappers.JobMapper;
import org.jobrunr.jobs.states.StateName;
import org.jobrunr.storage.PageRequest;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.utils.mapper.JsonMapper;
import org.jobrunr.utils.mapper.jackson.JacksonJsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private StorageProvider storageProvider;
    
    public JobController(StorageProvider storageProvider) {
		super();
		this.storageProvider = storageProvider;
	}

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getJobs(
            )throws URISyntaxException {
        try {
        	JsonMapper jsonMapper = new JacksonJsonMapper();
			JobMapper jobMapper = new JobMapper(jsonMapper);
        	List<Job> jobPage = storageProvider.getJobs(StateName.SUCCEEDED, PageRequest.ascOnUpdatedAt(100));
			
			List<String> jobs = new ArrayList<String>();
			
        	for (Job job : jobPage) {
        		String sJob = jobMapper.serializeJob(job);
        		jobs.add(sJob);
        		logger.info(sJob);
        	}
			return ResponseEntity.ok(jobs);
        	
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJobById(
    		 @PathVariable String id)
            throws URISyntaxException {
        try {
        	JsonMapper jsonMapper = new JacksonJsonMapper();
			JobMapper jobMapper = new JobMapper(jsonMapper);
        	logger.info("Getting details for: " + UUID.fromString(id).toString());
        	Job job = storageProvider.getJobById(UUID.fromString(id));
        	String sJob = jobMapper.serializeJob(job);
        	
        	return ResponseEntity.ok(sJob);
        	
        } catch (Exception ex) {
            // log exception first, then return Bad Request (400)
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
