package com.heroku.match.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heroku.match.exception.ApiError;
import com.heroku.match.exception.BadResourceException;
import com.heroku.match.exception.ResourceNotFoundException;
import com.heroku.match.model.Matched;
import com.heroku.match.service.MatchIntakeService;


@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String ROW_PER_PAGE = "50";

    @Autowired
    private MatchIntakeService matchIntakeService;

    @GetMapping(value = "/intake", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Matched>> matchIntake(
           @RequestParam(value = "page", defaultValue = "1") int pageNumber,
           @RequestParam(required = false) String name,
           @RequestParam(required = false, defaultValue = ROW_PER_PAGE)int rowPerPage) {

    	logger.debug("Matching intake leads for [" + name + "]");
    	
    	return ResponseEntity.ok(matchIntakeService.matchLeadByName(name, null));
    }

    @GetMapping(value = "/intake/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Matched>> matchById(
    		@PathVariable long id) {
    	try {
    		return ResponseEntity.ok(matchIntakeService.matchByIdAndSave(id));
    	} catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException ex) {
            // log exception first, then return Bad Request (400)
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/intake/range", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> matchByRange(
    		@RequestParam(value = "start", defaultValue = "1")long start
    		) {
    	try {
    		return ResponseEntity.ok(matchIntakeService.matchAll(start));
    	} catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException e) {
        	logger.error(e.getMessage());
        	ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getLocalizedMessage(),e.getErrorMessages());
        	return new ResponseEntity<Object>(
        		      apiError, new HttpHeaders(), apiError.getStatus());
        }
    }
    @GetMapping(value = "/intake/analyze", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> analyzeByRange(
    		@RequestParam(value = "start", defaultValue = "1")long start,
    		@RequestParam(value = "end", defaultValue = "0")long end,
    		@RequestParam(value = "save", defaultValue = "false")boolean save
    		) {
    	try {
    		
    		return ResponseEntity.ok(matchIntakeService.analyzeAll(start, end, save));
    		
    	} catch (ResourceNotFoundException ex) {
    		logger.error(ex.getMessage());
    		return ResponseEntity.status(HttpStatus.CONFLICT).build();
    	} catch (BadResourceException e) {
    		logger.error(e.getMessage());
    		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getLocalizedMessage(),e.getErrorMessages());
    		return new ResponseEntity<Object>(
    				apiError, new HttpHeaders(), apiError.getStatus());
    	}
    }

    @GetMapping(value = "/intake/analyzeall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> analyzeAll() {
    	try {
    		
    		return ResponseEntity.ok(matchIntakeService.analyzeAll());
    		
    	} catch (ResourceNotFoundException ex) {
    		logger.error(ex.getMessage());
    		return ResponseEntity.status(HttpStatus.CONFLICT).build();
    	} catch (BadResourceException e) {
    		logger.error(e.getMessage());
    		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getLocalizedMessage(),e.getErrorMessages());
    		return new ResponseEntity<Object>(
    				apiError, new HttpHeaders(), apiError.getStatus());
    	}
    }

}
