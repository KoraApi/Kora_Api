package com.kora.koramonoapi.History.api.rest;

import com.kora.koramonoapi.History.domain.service.FavoriteRecordService;
import com.kora.koramonoapi.History.mapping.FavoriteRecordMapper;
import com.kora.koramonoapi.History.resource.CreateFavoriteRecordResource;
import com.kora.koramonoapi.History.resource.FavoriteRecordResource;
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
@RequestMapping(value = "/api/v1/history-favorites/favorites", produces = "application/json")
@Tag(name = "Favorites", description = "Favorites Records operations: listing")
public class FavoriteRecordController {
    private final FavoriteRecordService favoriteRecordService;
    private final FavoriteRecordMapper mapper;

    public FavoriteRecordController(FavoriteRecordService favoriteRecordService, FavoriteRecordMapper mapper) {
        this.favoriteRecordService = favoriteRecordService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get all Favorites Records", description = "Returns Favorites list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping
    public Page<FavoriteRecordResource> getAll(@Parameter(hidden = true) @RequestHeader("Authorization") String jwt, Pageable pageable) {
        return mapper.modelListPage(favoriteRecordService.getAll(), pageable);
    }

    @Operation(summary = "Get Favorites Records by patient id", description = "Returns Favorites Records with a provided patient id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("byPatientId/{patientId}")
    public Page<FavoriteRecordResource> getFavoriteRecordByPatientId(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Patient Id", required = true, examples = @ExampleObject(name = "patientId", value = "1")) @PathVariable Integer patientId, Pageable pageable
    ) {
        return mapper.modelListPage(favoriteRecordService.getByPatientId(patientId), pageable);
    }

    @Operation(summary = "Get Favorites Records by patient id", description = "Returns Favorites Records with a provided patient id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })

    @GetMapping("isFavorite/{patientId}/{techniqueId}")
    public ResponseEntity<FavoriteRecordResource> getIsFavorite(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Patient Id", required = true, examples = @ExampleObject(name = "patientId", value = "1")) @PathVariable Integer patientId,
            @Parameter(description = "Technique Id", required = true, examples = @ExampleObject(name = "techniqueId", value = "1")) @PathVariable Integer techniqueId, Pageable pageable
    ) {
        return new ResponseEntity<>(mapper.toResource(favoriteRecordService.isFavorite(patientId,techniqueId)), HttpStatus.OK);
    }


    @Operation(summary = "Create Favorite Record", description = "Register a Favorite Record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @PostMapping
    public ResponseEntity<FavoriteRecordResource> createFavoriteRecord(
            //@Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            //@RequestBody CreateCertificationResource resource) {
            //return new ResponseEntity<>(mapper.toResource(certificationService.create(jwt, resource)), HttpStatus.CREATED);
            @RequestBody CreateFavoriteRecordResource resource,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            authorizationHeader = authorizationHeader.substring(7);
        }
        return new ResponseEntity<>(mapper.toResource(favoriteRecordService.create(authorizationHeader,(resource))), HttpStatus.CREATED);
    }


    @Operation(summary = "Delete a Favorite Record", description = "Delete a Favorite Record with a provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("{favoriteRecordId}")
    public ResponseEntity<?> deleteFavoriteRecord(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Favorite Record Id", required = true, examples = @ExampleObject(name = "FavoriteRecordId", value = "1")) @PathVariable Integer favoriteRecordId) {
        return favoriteRecordService.delete(jwt, favoriteRecordId);
    }
}
