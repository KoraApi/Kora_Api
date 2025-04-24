package com.kora.koramonoapi.History.api.rest;

import com.kora.koramonoapi.History.domain.service.HistoryRecordService;
import com.kora.koramonoapi.History.mapping.HistoryRecordMapper;
import com.kora.koramonoapi.History.resource.CreateHistoryRecordResource;
import com.kora.koramonoapi.History.resource.HistoryRecordResource;
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
@RequestMapping(value = "/api/v1/history-favorites/history", produces = "application/json")
@Tag(name = "History", description = "History Records operations: listing")
public class HistoryRecordController {
    private final HistoryRecordService historyRecordService;
    private final HistoryRecordMapper mapper;

    public HistoryRecordController(HistoryRecordService historyRecordService, HistoryRecordMapper mapper) {
        this.historyRecordService = historyRecordService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get all History Records", description = "Returns History list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping
    public Page<HistoryRecordResource> getAll(@Parameter(hidden = true) @RequestHeader("Authorization") String jwt, Pageable pageable) {
        return mapper.modelListPage(historyRecordService.getAll(), pageable);
    }

    @Operation(summary = "Get History Records by patient id", description = "Returns History Records with a provided patient id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("byPatientId/{patientId}")
    public Page<HistoryRecordResource> getHistoryRecordByPatientId(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Patient Id", required = true, examples = @ExampleObject(name = "patientId", value = "1")) @PathVariable Integer patientId, Pageable pageable
    ) {
        return mapper.modelListPage(historyRecordService.getByPatientId(patientId), pageable);
    }




    @Operation(summary = "Get History Records by patient id", description = "Returns History Records with a provided patient id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("byPatientIdAndDate/{patientId}/{year}/{month}/{day}")
    public Page<HistoryRecordResource> getByPatientIdAndDate(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Patient Id", required = true, examples = @ExampleObject(name = "patientId", value = "1")) @PathVariable Integer patientId,
            @Parameter(description = "Year", required = true, examples = @ExampleObject(name = "year", value = "2025")) @PathVariable int year,
            @Parameter(description = "Month", required = true, examples = @ExampleObject(name = "month", value = "3")) @PathVariable int month,
            @Parameter(description = "Day", required = true, examples = @ExampleObject(name = "day", value = "22")) @PathVariable int day, Pageable pageable
    ) {
        return mapper.modelListPage(historyRecordService.getByPatientIdAndDate(patientId,year,month,day), pageable);
    }


    @Operation(summary = "Create History Record", description = "Register a History Record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @PostMapping
    public ResponseEntity<HistoryRecordResource> createHistoryRecord(
            //@Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            //@RequestBody CreateCertificationResource resource) {
            //return new ResponseEntity<>(mapper.toResource(certificationService.create(jwt, resource)), HttpStatus.CREATED);
            @RequestBody CreateHistoryRecordResource resource,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            authorizationHeader = authorizationHeader.substring(7);
        }
        return new ResponseEntity<>(mapper.toResource(historyRecordService.create(authorizationHeader,(resource))), HttpStatus.CREATED);
    }


    @Operation(summary = "Delete a History Record", description = "Delete a History Record with a provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("{historyRecordId}")
    public ResponseEntity<?> deleteHistoryRecord(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "History Record Id", required = true, examples = @ExampleObject(name = "HistoryRecordId", value = "1")) @PathVariable Integer historyRecordId) {
        return historyRecordService.delete(jwt, historyRecordId);
    }
}
