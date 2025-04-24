package com.kora.koramonoapi.PersonalizedTechniques.api.rest;

import com.kora.koramonoapi.PersonalizedTechniques.domain.service.TechniqueService;
import com.kora.koramonoapi.PersonalizedTechniques.mapping.TechniqueMapper;
import com.kora.koramonoapi.PersonalizedTechniques.resource.Technique.TechniqueResource;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/personalized-techniques/techniques", produces = "application/json")
@Tag(name = "Techniques", description = "Techniques operations: listing")
public class TechniqueController {
    private final TechniqueService techniqueService;
    private final TechniqueMapper mapper;

    public TechniqueController(TechniqueService techniqueService, TechniqueMapper mapper) {
        this.techniqueService = techniqueService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get all techniques", description = "Returns techniques list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping
    public Page<TechniqueResource> getAll(@Parameter(hidden = true) @RequestHeader("Authorization") String jwt, Pageable pageable) {
        return mapper.modelListPage(techniqueService.getAll(), pageable);
    }

    @Operation(summary = "Get techniques by id", description = "Returns techniques with a provided id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("{techniqueId}")
    public TechniqueResource getById(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Technique Id", required = true, examples = @ExampleObject(name = "techniqueId", value = "1")) @PathVariable Integer techniqueId
    ) {
        return mapper.toResource(techniqueService.getById(techniqueId));
    }

    @Operation(summary = "Get techniques by category", description = "Returns techniques with a provided category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("byCategory/{category}")
    public Page<TechniqueResource> getTechniquesByCategory(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "Category", required = true, examples = @ExampleObject(name = "category", value = "MUSICTHERAPY")) @PathVariable String category, Pageable pageable
    ) {
        return mapper.modelListPage(techniqueService.getByCategory(category), pageable);
    }
}
