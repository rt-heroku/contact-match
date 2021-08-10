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
@RequestMapping("/api/incomingleads")
public class IncomingLeadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int ROW_PER_PAGE = 5;

    @Autowired
    private IncomingLeadService incomingLeadService;

    @GetMapping(value = "/similarity", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IncomingLead>> findIncomingLeadBySimilarity(
             @RequestParam(value = "page", defaultValue = "1") int pageNumber,
             @RequestParam(required = false) String name) {
    	try {
    		List<IncomingLead> names = incomingLeadService.findBySimilarity(name, pageNumber, ROW_PER_PAGE);
			return ResponseEntity.ok(names);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
}

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IncomingLead>> findAll(
           @RequestParam(value = "page", defaultValue = "1") int pageNumber,
           @RequestParam(required = false) String name) {
        if (!StringUtils.hasText(name)) {
            return ResponseEntity.ok(incomingLeadService.findAll(pageNumber, ROW_PER_PAGE));
        } else {
            return ResponseEntity.ok(incomingLeadService.findAllByName(name, pageNumber, ROW_PER_PAGE));
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomingLead> findIncomingLeadById(
            @PathVariable long id) {
        try {
            IncomingLead incomingLead = incomingLeadService.findById(id);
            return ResponseEntity.ok(incomingLead);  // return 200, with json body
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<IncomingLead> addIncomingLead(
            @Valid @RequestBody IncomingLead intakeLead)
            throws URISyntaxException {
        try {
            IncomingLead newIncomingLead = incomingLeadService.save(intakeLead);
            return ResponseEntity.created(new URI("/api/incomingleads/" + newIncomingLead.getId()))
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<IncomingLead> updateIncomingLead(
            @PathVariable long id,
            @Valid @RequestBody IncomingLead incomingLead) {
        try {
            incomingLead.setId(id);
            incomingLeadService.update(incomingLead);
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


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteIncomingLeadById(
            @PathVariable long id) {
        try {
            incomingLeadService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
