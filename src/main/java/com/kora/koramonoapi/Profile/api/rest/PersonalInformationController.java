package com.kora.koramonoapi.Profile.api.rest;

import com.kora.koramonoapi.Profile.domain.service.PersonalInformationService;
import com.kora.koramonoapi.Profile.mapping.PersonalInformationMapper;
import com.kora.koramonoapi.Profile.resource.CreatePersonalInformationResource;
import com.kora.koramonoapi.Profile.resource.PersonalInformationResource;
import com.kora.koramonoapi.Profile.resource.UpdatePersonalInformationResource;
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
@RequestMapping(value = "/api/v1/personal-information/profiles", produces = "application/json")
@Tag(name = "Profiles", description = "Profile operations: listing, retrieval, creation, update, and deletion")
public class PersonalInformationController {
    private final PersonalInformationService personalInformationService;
    private final PersonalInformationMapper mapper;

    public PersonalInformationController(PersonalInformationService personalInformationService, PersonalInformationMapper mapper) {
        this.personalInformationService = personalInformationService;
        this.mapper = mapper;
    }
    @Operation(summary = "Get all personal information", description = "Returns personal information list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping
    public Page<PersonalInformationResource> getAllPersonalInformation(@Parameter(hidden = true) @RequestHeader("Authorization") String jwt, Pageable pageable) {
        return mapper.modelListPage(personalInformationService.getAll(), pageable);
    }
    @Operation(summary = "Get personal information by id", description = "Returns personal information with a provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("{personalInformationId}")
    public PersonalInformationResource getPersonalInformationById(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Personal Information Id", required = true, examples = @ExampleObject(name = "personalInformationId", value = "1")) @PathVariable Integer personalInformationId
    ) {
        return mapper.toResource(personalInformationService.getById(personalInformationId));
    }

    @Operation(summary = "Get personal information by patient id", description = "Returns personal information with a provided patient id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("byPatientId/{patientId}")
    public Page<PersonalInformationResource> getPersonalInformationByPatientId(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Patient Id", required = true, examples = @ExampleObject(name = "patientId", value = "1")) @PathVariable Integer patientId, Pageable pageable
    ) {
        return mapper.modelListPage(personalInformationService.getByPatientId(patientId), pageable);
    }
    @Operation(summary = "Create personal information", description = "Register a personal information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @PostMapping
    public ResponseEntity<PersonalInformationResource> createPersonalInformation(
            //@Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            //@RequestBody CreateCertificationResource resource) {
            //return new ResponseEntity<>(mapper.toResource(certificationService.create(jwt, resource)), HttpStatus.CREATED);
            @RequestBody CreatePersonalInformationResource resource,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            authorizationHeader = authorizationHeader.substring(7);
        }
        return new ResponseEntity<>(mapper.toResource(personalInformationService.create(authorizationHeader,(resource))), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a personal information partially", description = "Updates a personal information partially based on the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @PatchMapping("{personalInformationId}")
    public ResponseEntity<PersonalInformationResource> patchPersonalInformation(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Personal Information Id", required = true, examples = @ExampleObject(name = "personalInformationId", value = "1")) @PathVariable Integer personalInformationId,
            @RequestBody UpdatePersonalInformationResource request
    ) {
        return new ResponseEntity<>(mapper.toResource(personalInformationService.update(jwt, personalInformationId,request)), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a personal information", description = "Delete a personal information with a provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("{personalInformationId}")
    public ResponseEntity<?> deletePersonalInformation(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Personal Information Id", required = true, examples = @ExampleObject(name = "personalInformationId", value = "1")) @PathVariable Integer personalInformationId) {
        return personalInformationService.delete(jwt, personalInformationId);
    }
}
