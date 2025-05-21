package com.kora.koramonoapi.AnxietyBiometricData.api.rest;

import com.kora.koramonoapi.AnxietyBiometricData.domain.service.SelfPerceptionService;
import com.kora.koramonoapi.AnxietyBiometricData.mapping.SelfPerceptionMapper;
import com.kora.koramonoapi.AnxietyBiometricData.resource.SelfPerception.CreateSelfPerceptionResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.SelfPerception.SelfPerceptionResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.SelfPerception.UpdateSelfPerceptionResource;
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
@RequestMapping(value = "/api/v1/health-data/self-perception", produces = "application/json")
@Tag(name = "Self Perception", description = "Self Perception Data operations: listing, retrieval, creation, update, and deletion")
public class SelfPerceptionController {
    private final SelfPerceptionService selfPerceptionService;
    private final SelfPerceptionMapper mapper;

    public SelfPerceptionController(SelfPerceptionService selfPerceptionService, SelfPerceptionMapper mapper) {
        this.selfPerceptionService = selfPerceptionService;
        this.mapper = mapper;
    }


    @Operation(summary = "Get all self-perception data", description = "Returns self-perception data list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping
    public Page<SelfPerceptionResource> getAllSelfPerceptionData(@Parameter(hidden = true) @RequestHeader("Authorization") String jwt, Pageable pageable) {
        return mapper.modelListPage(selfPerceptionService.getAll(), pageable);
    }

    @Operation(summary = "Get self-perception data by id", description = "Returns self-perception data with a provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("{selfPerceptionId}")
    public SelfPerceptionResource getSelfPerceptionDataById(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Self Perception Id", required = true, examples = @ExampleObject(name = "selfPerceptionId", value = "1")) @PathVariable Integer selfPerceptionId
    ) {
        return mapper.toResource(selfPerceptionService.getById(selfPerceptionId));
    }


    @Operation(summary = "Get self-perception data by patient id", description = "Returns self-perception data with a provided patient id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("byPatientId/{patientId}")
    public Page<SelfPerceptionResource> getSelfPerceptionDataByPatientId(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Patient Id", required = true, examples = @ExampleObject(name = "patientId", value = "1")) @PathVariable Integer patientId, Pageable pageable
    ) {
        return mapper.modelListPage(selfPerceptionService.getByPatientId(patientId), pageable);
    }

    @Operation(summary = "Get self-perception data by patient id and year & month", description = "Returns self-perception data with a provided patient id, year & month")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("byPatientIdAndDate/{patientId}/{year}/{month}")
    public Page<SelfPerceptionResource> getSelfPerceptionDataByPatientIdAndDate(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Patient Id", required = true, examples = @ExampleObject(name = "patientId", value = "1")) @PathVariable Integer patientId,
            @Parameter(description = "Year", required = true, examples = @ExampleObject(name = "year", value = "2025")) @PathVariable Integer year,
            @Parameter(description = "Month", required = true, examples = @ExampleObject(name = "month", value = "3")) @PathVariable Integer month,Pageable pageable
    ) {
        return mapper.modelListPage(selfPerceptionService.getByPatientIdAndDate(patientId,year,month), pageable);
    }


    @Operation(summary = "Create self-perception data", description = "Register a self-perception data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @PostMapping
    public ResponseEntity<SelfPerceptionResource> createSelfPerceptionData(
            @RequestBody CreateSelfPerceptionResource resource,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            authorizationHeader = authorizationHeader.substring(7);
        }
        return new ResponseEntity<>(mapper.toResource(selfPerceptionService.create(authorizationHeader,(resource))), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a self-perception data partially", description = "Updates a self-perception data partially based on the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @PatchMapping("{selfPerceptionId}")
    public ResponseEntity<SelfPerceptionResource> patchSelfPerceptionData(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Self Perception Data Id", required = true, examples = @ExampleObject(name = "selfPerceptionId", value = "1")) @PathVariable Integer selfPerceptionId,
            @RequestBody UpdateSelfPerceptionResource request
    ) {
        return new ResponseEntity<>(mapper.toResource(selfPerceptionService.update(jwt, selfPerceptionId,request)), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a self-perception data", description = "Delete a self-perception data with a provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("{selfPerceptionId}")
    public ResponseEntity<?> deleteSelfPerceptionData(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Self Perception Data Id", required = true, examples = @ExampleObject(name = "selfPerceptionId", value = "1")) @PathVariable Integer selfPerceptionId) {
        return selfPerceptionService.delete(jwt, selfPerceptionId);
    }
}
