package com.kora.koramonoapi.AnxietyBiometricData.api.rest;

import com.kora.koramonoapi.AnxietyBiometricData.domain.service.AnxietyAssessmentService;
import com.kora.koramonoapi.AnxietyBiometricData.mapping.AnxietyAssessmentMapper;
import com.kora.koramonoapi.AnxietyBiometricData.resource.AnxietyAssessment.AnxietyAssessmentResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.AnxietyAssessment.CreateAnxietyAssessmentResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.AnxietyAssessment.UpdateAnxietyAssessmentResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.PhysiologicalDataResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/health-data/anxiety-assessments", produces = "application/json")
@Tag(name = "Anxiety Assessments", description = "Anxiety Assessments operations: listing, retrieval, creation, update, and deletion")
public class AnxietyAssessmentController {
    private final AnxietyAssessmentService anxietyAssessmentService;
    private final AnxietyAssessmentMapper mapper;

    public AnxietyAssessmentController(AnxietyAssessmentService anxietyAssessmentService, AnxietyAssessmentMapper mapper) {
        this.anxietyAssessmentService = anxietyAssessmentService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get all anxiety assessments", description = "Returns anxiety assessments list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping
    public Page<AnxietyAssessmentResource> getAllAnxietyAssessments(@Parameter(hidden = true) @RequestHeader("Authorization") String jwt, Pageable pageable) {
        return mapper.modelListPage(anxietyAssessmentService.getAll(), pageable);
    }

    @Operation(summary = "Get anxiety assessment by id", description = "Returns anxiety assessment with a provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("{anxietyAssessmentId}")
    public AnxietyAssessmentResource getAnxietyAssessmentById(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Anxiety Assessment Id", required = true, examples = @ExampleObject(name = "anxietyAssessmentId", value = "1")) @PathVariable Integer anxietyAssessmentId
    ) {
        return mapper.toResource(anxietyAssessmentService.getById(anxietyAssessmentId));
    }

    @Operation(summary = "Get anxiety assessments by patient id", description = "Returns anxiety assessments with a provided patient id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("byPatientId/{patientId}")
    public Page<AnxietyAssessmentResource> getAnxietyAssessmentsByPatientId(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Patient Id", required = true, examples = @ExampleObject(name = "patientId", value = "1")) @PathVariable Integer patientId, Pageable pageable
    ) {
        return mapper.modelListPage(anxietyAssessmentService.getByPatientId(patientId), pageable);
    }


    @Operation(summary = "Get anxiety data by patient id and year & month", description = "Returns anxiety data with a provided patient id, year & month")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("byPatientIdAndDate/{patientId}/{year}/{month}")
    public Page<AnxietyAssessmentResource> getAnxietyDataByPatientIdAndDate(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Patient Id", required = true, examples = @ExampleObject(name = "patientId", value = "1")) @PathVariable Integer patientId,
            @Parameter(description = "Year", required = true, examples = @ExampleObject(name = "year", value = "2025")) @PathVariable Integer year,
            @Parameter(description = "Month", required = true, examples = @ExampleObject(name = "month", value = "3")) @PathVariable Integer month,Pageable pageable
    ) {
        return mapper.modelListPage(anxietyAssessmentService.getByPatientIdAndDate(patientId,year,month), pageable);
    }


    @Operation(summary = "Create anxiety assessment", description = "Register a anxiety assessment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @PostMapping
    public ResponseEntity<AnxietyAssessmentResource> createAnxietyAssessment(
            //@Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            //@RequestBody CreateCertificationResource resource) {
            //return new ResponseEntity<>(mapper.toResource(certificationService.create(jwt, resource)), HttpStatus.CREATED);
            @RequestBody CreateAnxietyAssessmentResource resource,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            authorizationHeader = authorizationHeader.substring(7);
        }
        return new ResponseEntity<>(mapper.toResource(anxietyAssessmentService.create(authorizationHeader,(resource))), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a anxiety assessment partially", description = "Updates a anxiety assessment partially based on the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @PatchMapping("{anxietyAssessmentId}")
    public ResponseEntity<AnxietyAssessmentResource> patchAnxietyAssessment(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Anxiety Assessment Id", required = true, examples = @ExampleObject(name = "anxietyAssessmentId", value = "1")) @PathVariable Integer anxietyAssessmentId,
            @RequestBody UpdateAnxietyAssessmentResource request
    ) {
        return new ResponseEntity<>(mapper.toResource(anxietyAssessmentService.update(jwt, anxietyAssessmentId,request)), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a anxiety assessment", description = "Delete a anxiety assessment with a provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("{anxietyAssessmentId}")
    public ResponseEntity<?> deleteAnxietyAssessment(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Anxiety Assessment Id", required = true, examples = @ExampleObject(name = "anxietyAssessmentId", value = "1")) @PathVariable Integer anxietyAssessmentId) {
        return anxietyAssessmentService.delete(jwt, anxietyAssessmentId);
    }
}
