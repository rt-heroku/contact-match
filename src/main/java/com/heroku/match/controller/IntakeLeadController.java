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

import com.heroku.match.domain.IntakeLead;
import com.heroku.match.exception.BadResourceException;
import com.heroku.match.exception.ResourceAlreadyExistsException;
import com.heroku.match.exception.ResourceNotFoundException;
import com.heroku.match.service.IntakeLeadService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api( //description = "Endpoints for Creating, Retrieving, Updating and Deleting of IntakeLeads.",
        tags = {"intakelead"})
@RestController
@RequestMapping("/api")
public class IntakeLeadController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int ROW_PER_PAGE = 5;

    @Autowired
    private IntakeLeadService nameService;

    @ApiOperation(value = "Find intakeLead by similarity", notes = "Full IntakeLead search by intakeLead", tags = {"intakeLead"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = List.class)})
    @GetMapping(value = "/similarity", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IntakeLead>> findIntakeLeadBySimilarity(
            @ApiParam(name = "page",
                    value = "Page number, default is 1",
                    example = "1",
                    required = false) @RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @ApiParam("IntakeLead of the person to search for.") @RequestParam(required = false) String intakeLead) {
    	try {
    		List<IntakeLead> names = nameService.findBySimilarity(intakeLead, pageNumber, ROW_PER_PAGE);
			return ResponseEntity.ok(names);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
}

    @ApiOperation(value = "Find by full intakeLead", notes = "Full IntakeLead search by %intakeLead% format", tags = {"intakeLead"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = List.class)})
    @GetMapping(value = "/names", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IntakeLead>> findAll(
            @ApiParam(name = "page",
                    value = "Page number, default is 1",
                    example = "1",
                    required = false) @RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @ApiParam("IntakeLead of the intakeLead for search.") @RequestParam(required = false) String intakeLead) {
        if (StringUtils.hasText(intakeLead)) {
            return ResponseEntity.ok(nameService.findAll(pageNumber, ROW_PER_PAGE));
        } else {
            return ResponseEntity.ok(nameService.findAllByName(intakeLead, pageNumber, ROW_PER_PAGE));
        }
    }

    @ApiOperation(value = "Find intakeLead by ID", notes = "Returns a single intakeLead", tags = {"intakeLead"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation", response = IntakeLead.class),
        @ApiResponse(code = 404, message = "IntakeLead not found")})
    @GetMapping(value = "/names/{nameId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IntakeLead> findIntakeLeadById(
            @ApiParam(name = "nameId",
                    value = "Id of the intakeLead to be obtained. Cannot be empty.",
                    example = "1",
                    required = true)
            @PathVariable long nameId) {
        try {
            IntakeLead intakeLead = nameService.findById(nameId);
            return ResponseEntity.ok(intakeLead);  // return 200, with json body
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }

    @ApiOperation(value = "Add a new intakeLead", tags = {"intakeLead"})
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "IntakeLead created"),
        @ApiResponse(code = 400, message = "Invalid input"),
        @ApiResponse(code = 409, message = "IntakeLead already exists")})
    @PostMapping(value = "/names")
    public ResponseEntity<IntakeLead> addIntakeLead(
            @ApiParam("IntakeLead to add. Cannot null or empty.")
            @Valid @RequestBody IntakeLead intakeLead)
            throws URISyntaxException {
        try {
            IntakeLead newIntakeLead = nameService.save(intakeLead);
            return ResponseEntity.created(new URI("/api/names/" + newIntakeLead.getId()))
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

    @ApiOperation(value = "Update an existing intakeLead", tags = {"intakeLead"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation"),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "IntakeLead not found"),
        @ApiResponse(code = 405, message = "Validation exception")})
    @PutMapping(value = "/names/{nameId}")
    public ResponseEntity<IntakeLead> updateIntakeLead(
            @ApiParam(name = "nameId",
                    value = "Id of the intakeLead to be update. Cannot be empty.",
                    example = "1",
                    required = true)
            @PathVariable long nameId,
            @ApiParam("IntakeLead to update. Cannot null or empty.")
            @Valid @RequestBody IntakeLead intakeLead) {
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


    @ApiOperation(value = "Deletes a intakeLead", tags = {"intakeLead"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "successful operation"),
        @ApiResponse(code = 404, message = "IntakeLead not found")})
    @DeleteMapping(path = "/names/{nameId}")
    public ResponseEntity<Void> deleteIntakeLeadById(
            @ApiParam(name = "nameId",
                    value = "Id of the intakeLead to be delete. Cannot be empty.",
                    example = "1",
                    required = true)
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
