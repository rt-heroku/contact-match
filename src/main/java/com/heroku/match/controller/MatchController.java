package com.heroku.match.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heroku.match.exception.ApiError;
import com.heroku.match.exception.JobException;
import com.heroku.match.service.MatchIntakeService;

@RestController
@RequestMapping("/api/match")
public class MatchController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String ROW_PER_PAGE = "100";

	@Autowired
	private MatchIntakeService matchIntakeService;

	@GetMapping(value = "/intake", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> match() {
		try {
			return ResponseEntity.ok(matchIntakeService.matchAll());
		} catch (JobException e) {
			logger.error(e.getMessage());
			ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(),
					e.getErrorMessages());
			return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
		}
	}

	@GetMapping(value = "/intake/analyze", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> analyzeAll() {
		try {
			return ResponseEntity.ok(matchIntakeService.analyzeAll());
		} catch (JobException e) {
			logger.error(e.getMessage());
			ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(),
					e.getErrorMessages());
			return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
		}
	}

	@GetMapping(value = "/intake/company/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> matchIntake(@PathVariable String name) 
	{
		try {
			return ResponseEntity.ok(matchIntakeService.matchLeadByCompanyName(name, null));
		} catch (JobException e) {
			logger.error(e.getMessage());
			ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(),
					e.getErrorMessages());
			return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
		}
	}

	@GetMapping(value = "/intake/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> matchById(@PathVariable long id) {
		try {
			return ResponseEntity.ok(matchIntakeService.matchByIdAndSave(id));
		} catch (JobException e) {
			logger.error(e.getMessage());
			ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage(),
					e.getErrorMessages());
			return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
		}
	}

}
