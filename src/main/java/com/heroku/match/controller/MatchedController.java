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

import com.heroku.match.exception.BadResourceException;
import com.heroku.match.exception.ResourceAlreadyExistsException;
import com.heroku.match.exception.ResourceNotFoundException;
import com.heroku.match.model.Matched;
import com.heroku.match.service.MatchedService;


@RestController
@RequestMapping("/api/matched")
public class MatchedController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int ROW_PER_PAGE = 100;

    @Autowired
    private MatchedService matchedService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Matched>> findAll(
           @RequestParam(value = "page", defaultValue = "1") int pageNumber,
           @RequestParam(required = false) String name) {
        if (!StringUtils.hasText(name)) {
            return ResponseEntity.ok(matchedService.findAll(pageNumber, ROW_PER_PAGE));
        } else {
            return ResponseEntity.ok(matchedService.findAllByName(name, pageNumber, ROW_PER_PAGE));
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Matched> findMatchedById(
            @PathVariable long id) {
        try {
            Matched matched = matchedService.findById(id);
            return ResponseEntity.ok(matched);  // return 200, with json body
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Matched> addMatched(
            @Valid @RequestBody Matched matched)
            throws URISyntaxException {
        try {
            Matched newMatched = matchedService.save(matched);
            return ResponseEntity.created(new URI("/api/matcheds/" + newMatched.getMatchId()))
                    .body(matched);
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
    public ResponseEntity<Matched> updateMatched(
            @PathVariable long id,
            @Valid @RequestBody Matched matched) {
        try {
            matched.setMatchId(id);
            matchedService.update(matched);
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
    public ResponseEntity<Void> deleteMatchedById(
            @PathVariable long id) {
        try {
            matchedService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
