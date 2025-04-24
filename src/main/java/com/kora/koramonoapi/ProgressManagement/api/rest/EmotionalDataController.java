package com.kora.koramonoapi.ProgressManagement.api.rest;

import com.kora.koramonoapi.ProgressManagement.domain.service.EmotionalDataService;
import com.kora.koramonoapi.ProgressManagement.mapping.EmotionalDataMapper;
import com.kora.koramonoapi.ProgressManagement.resource.CreateEmotionalDataResource;
import com.kora.koramonoapi.ProgressManagement.resource.EmotionalDataResource;
import com.kora.koramonoapi.ProgressManagement.resource.UpdateEmotionalDataResource;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/v1/progress-management/emotional-data", produces = "application/json")
@Tag(name = "Emotional Data", description = "Emotional Data operations: listing, retrieval, creation, update, and deletion")
public class EmotionalDataController {
    private final EmotionalDataService emotionalDataService;
    private final EmotionalDataMapper mapper;

    public EmotionalDataController(EmotionalDataService emotionalDataService, EmotionalDataMapper mapper) {
        this.emotionalDataService = emotionalDataService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get all emotional data", description = "Returns emotional data list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping
    public Page<EmotionalDataResource> getAllEmotionalData(@Parameter(hidden = true) @RequestHeader("Authorization") String jwt, Pageable pageable) {
        return mapper.modelListPage(emotionalDataService.getAll(), pageable);
    }
    @Operation(summary = "Get emotional data by id", description = "Returns emotional data with a provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("{emotionalDataId}")
    public EmotionalDataResource getEmotionalDataById(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Emotional Data Id", required = true, examples = @ExampleObject(name = "emotionalDataId", value = "1")) @PathVariable Integer emotionalDataId
    ) {
        return mapper.toResource(emotionalDataService.getById(emotionalDataId));
    }

    @Operation(summary = "Get emotional data by patient id", description = "Returns emotional data with a provided patient id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("byPatientId/{patientId}")
    public Page<EmotionalDataResource> getEmotionalDataByPatientId(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Patient Id", required = true, examples = @ExampleObject(name = "patientId", value = "1")) @PathVariable Integer patientId, Pageable pageable
    ) {
        return mapper.modelListPage(emotionalDataService.getByPatientId(patientId), pageable);
    }
    @Operation(summary = "Create emotional data", description = "Register an emotional data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @PostMapping
    public ResponseEntity<EmotionalDataResource> createEmotionalData(
            //@Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            //@RequestBody CreateCertificationResource resource) {
            //return new ResponseEntity<>(mapper.toResource(certificationService.create(jwt, resource)), HttpStatus.CREATED);
            @RequestBody CreateEmotionalDataResource resource,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            authorizationHeader = authorizationHeader.substring(7);
        }
        return new ResponseEntity<>(mapper.toResource(emotionalDataService.create(authorizationHeader,(resource))), HttpStatus.CREATED);
    }

    @Operation(summary = "Update an emotional data partially", description = "Updates an emotional data partially based on the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @PatchMapping("{emotionalDataId}")
    public ResponseEntity<EmotionalDataResource> patchEmotionalData(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Emotional Data Id", required = true, examples = @ExampleObject(name = "emotionalDataId", value = "1")) @PathVariable Integer emotionalDataId,
            @RequestBody UpdateEmotionalDataResource request
    ) {
        return new ResponseEntity<>(mapper.toResource(emotionalDataService.update(jwt, emotionalDataId,request)), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete an emotional data", description = "Delete an emotional data with a provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("{emotionalDataId}")
    public ResponseEntity<?> deleteEmotionalData(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Emotional Data Id", required = true, examples = @ExampleObject(name = "emotionalDataId", value = "1")) @PathVariable Integer emotionalDataId) {
        return emotionalDataService.delete(jwt, emotionalDataId);
    }

    @Operation(summary = "Get emotional data by patient ID and date range", description = "Returns emotional data for a patient within a specified date range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got emotional data in range"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("byPatientId/{patientId}/date-range")
    public Page<EmotionalDataResource> getEmotionalDataByPatientIdAndDateRange(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Patient ID", required = true) @PathVariable Integer patientId,
            @Parameter(description = "Start date", required = true, example = "2023-01-01T00:00:00")
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @Parameter(description = "End date", required = true, example = "2023-12-31T23:59:59")
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate, Pageable pageable) {

        return mapper.modelListPage(emotionalDataService.getByPatientIdAndDateRange(patientId, startDate, endDate), pageable);
    }

}
