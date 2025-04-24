package com.kora.koramonoapi.AnxietyBiometricData.api.rest;

import com.kora.koramonoapi.AnxietyBiometricData.domain.service.PhysiologicalDataService;
import com.kora.koramonoapi.AnxietyBiometricData.mapping.PhysiologicalDataMapper;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.CreatePhysiologicalDataResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.PhysiologicalDataResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.UpdatePhysiologicalDataResource;
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
@RequestMapping(value = "/api/v1/health-data/physiological-data", produces = "application/json")
@Tag(name = "Physiological Data", description = "Physiological Data operations: listing, retrieval, creation, update, and deletion")
public class PhysiologicalDataController {
    private final PhysiologicalDataService physiologicalDataService;
    private final PhysiologicalDataMapper mapper;

    public PhysiologicalDataController(PhysiologicalDataService physiologicalDataService, PhysiologicalDataMapper mapper) {
        this.physiologicalDataService = physiologicalDataService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get all physiological data", description = "Returns physiological data list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping
    public Page<PhysiologicalDataResource> getAllPhysiologicalData(@Parameter(hidden = true) @RequestHeader("Authorization") String jwt, Pageable pageable) {
        return mapper.modelListPage(physiologicalDataService.getAll(), pageable);
    }

    @Operation(summary = "Get physiological data by id", description = "Returns physiological data with a provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("{physiologicalDataId}")
    public PhysiologicalDataResource getPhysiologicalDataById(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Physiological Data Id", required = true, examples = @ExampleObject(name = "physiologicalDataId", value = "1")) @PathVariable Integer physiologicalDataId
    ) {
        return mapper.toResource(physiologicalDataService.getById(physiologicalDataId));
    }


    @Operation(summary = "Get physiological data by patient id", description = "Returns physiological data with a provided patient id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("byPatientId/{patientId}")
    public Page<PhysiologicalDataResource> getPhysiologicalDataByPatientId(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Patient Id", required = true, examples = @ExampleObject(name = "patientId", value = "1")) @PathVariable Integer patientId, Pageable pageable
    ) {
        return mapper.modelListPage(physiologicalDataService.getByPatientId(patientId), pageable);
    }

    @Operation(summary = "Get physiological data by patient id and year & month", description = "Returns physiological data with a provided patient id, year & month")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("byPatientIdAndDate/{patientId}/{year}/{month}")
    public Page<PhysiologicalDataResource> getPhysiologicalDataByPatientIdAndDate(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Patient Id", required = true, examples = @ExampleObject(name = "patientId", value = "1")) @PathVariable Integer patientId,
            @Parameter(description = "Year", required = true, examples = @ExampleObject(name = "year", value = "2025")) @PathVariable Integer year,
            @Parameter(description = "Month", required = true, examples = @ExampleObject(name = "month", value = "3")) @PathVariable Integer month,Pageable pageable
    ) {
        return mapper.modelListPage(physiologicalDataService.getByPatientIdAndDate(patientId,year,month), pageable);
    }


    @Operation(summary = "Create physiological data", description = "Register a physiological data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @PostMapping
    public ResponseEntity<PhysiologicalDataResource> createPhysiologicalData(
            //@Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            //@RequestBody CreateCertificationResource resource) {
            //return new ResponseEntity<>(mapper.toResource(certificationService.create(jwt, resource)), HttpStatus.CREATED);
            @RequestBody CreatePhysiologicalDataResource resource,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            authorizationHeader = authorizationHeader.substring(7);
        }
        return new ResponseEntity<>(mapper.toResource(physiologicalDataService.create(authorizationHeader,(resource))), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a physiological data partially", description = "Updates a physiological data partially based on the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @PatchMapping("{physiologicalDataId}")
    public ResponseEntity<PhysiologicalDataResource> patchPhysiologicalData(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Physiological Data Id", required = true, examples = @ExampleObject(name = "physiologicalDataId", value = "1")) @PathVariable Integer physiologicalDataId,
            @RequestBody UpdatePhysiologicalDataResource request
    ) {
        return new ResponseEntity<>(mapper.toResource(physiologicalDataService.update(jwt, physiologicalDataId,request)), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a physiological data", description = "Delete a physiological data with a provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("{physiologicalDataId}")
    public ResponseEntity<?> deletePhysiologicalData(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Physiological Data Id", required = true, examples = @ExampleObject(name = "physiologicalDataId", value = "1")) @PathVariable Integer physiologicalDataId) {
        return physiologicalDataService.delete(jwt, physiologicalDataId);
    }
}
