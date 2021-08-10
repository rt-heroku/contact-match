package com.heroku.match.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heroku.match.domain.IncomingLead;
import com.heroku.match.exception.BadResourceException;
import com.heroku.match.exception.ResourceAlreadyExistsException;
import com.heroku.match.exception.ResourceNotFoundException;
import com.heroku.match.service.IncomingLeadService;


@RestController
@RequestMapping("/api")
public class IncomingLeadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int ROW_PER_PAGE = 5;

    @Autowired
    private IncomingLeadService nameService;

    @GetMapping(value = "/similarity", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IncomingLead>> findIncomingLeadBySimilarity(
             @RequestParam(value = "page", defaultValue = "1") int pageNumber,
             @RequestParam(required = false) String intakeLead) {
    	try {
    		List<IncomingLead> names = nameService.findBySimilarity(intakeLead, pageNumber, ROW_PER_PAGE);
			return ResponseEntity.ok(names);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
}

    @GetMapping(value = "/names", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IncomingLead>> findAll(
           @RequestParam(value = "page", defaultValue = "1") int pageNumber,
           @RequestParam(required = false) String intakeLead) {
        if (!StringUtils.hasText(intakeLead)) {
            return ResponseEntity.ok(nameService.findAll(pageNumber, ROW_PER_PAGE));
        } else {
            return ResponseEntity.ok(nameService.findAllByName(intakeLead, pageNumber, ROW_PER_PAGE));
        }
    }

    @GetMapping(value = "/names/{nameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomingLead> findIncomingLeadById(
            @PathVariable long nameId) {
        try {
            IncomingLead intakeLead = nameService.findById(nameId);
            return ResponseEntity.ok(intakeLead);  // return 200, with json body
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }

    @PostMapping(value = "/names")
    public ResponseEntity<IncomingLead> addIncomingLead(
            @Valid @RequestBody IncomingLead intakeLead)
            throws URISyntaxException {
        try {
            IncomingLead newIncomingLead = nameService.save(intakeLead);
            return ResponseEntity.created(new URI("/api/names/" + newIncomingLead.getId()))
                    .body(intakeLead);
        } catch (ResourceAlreadyExistsException ex) {
            // log exception first, then return Conflict (409)
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException ex) {
            // log exception first, then return Bad Request (400)
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/names/{nameId}")
    public ResponseEntity<IncomingLead> updateIncomingLead(
            @PathVariable long nameId,
            @Valid @RequestBody IncomingLead intakeLead) {
        try {
            intakeLead.setId(nameId);
            nameService.update(intakeLead);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            // log exception first, then return Not Found (404)
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        } catch (BadResourceException ex) {
            // log exception first, then return Bad Request (400)
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @DeleteMapping(path = "/names/{nameId}")
    public ResponseEntity<Void> deleteIncomingLeadById(
            @PathVariable long nameId) {
        try {
            nameService.deleteById(nameId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}