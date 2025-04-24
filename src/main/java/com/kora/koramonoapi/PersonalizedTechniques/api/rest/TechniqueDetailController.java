package com.kora.koramonoapi.PersonalizedTechniques.api.rest;

import com.kora.koramonoapi.PersonalizedTechniques.domain.service.TechniqueDetailService;
import com.kora.koramonoapi.PersonalizedTechniques.mapping.TechniqueDetailMapper;
import com.kora.koramonoapi.PersonalizedTechniques.resource.TechniqueDetail.TechniqueDetailResource;
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
@RequestMapping(value = "/api/v1/personalized-techniques/techniques-details", produces = "application/json")
@Tag(name = "Techniques details", description = "Techniques details operations: listing")
public class TechniqueDetailController {
    private final TechniqueDetailService techniqueDetailService;
    private final TechniqueDetailMapper mapper;

    public TechniqueDetailController(TechniqueDetailService techniqueService, TechniqueDetailMapper mapper) {
        this.techniqueDetailService= techniqueService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get all techniques details", description = "Returns techniques list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping
    public Page<TechniqueDetailResource> getAll(@Parameter(hidden = true) @RequestHeader("Authorization") String jwt, Pageable pageable) {
        return mapper.modelListPage(techniqueDetailService.getAll(), pageable);
    }


    @Operation(summary = "Get details by techniqueId", description = "Returns details with a provided techniqueId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully got"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("byTechniqueId/{techniqueId}")
    public Page<TechniqueDetailResource> getDetailsByTechniqueId(
            @Parameter(hidden = true) @RequestHeader("Authorization") String jwt,
            @Parameter(description = "techniqueId", required = true, examples = @ExampleObject(name = "techniqueId", value = "1")) @PathVariable Integer techniqueId, Pageable pageable
    ) {
        return mapper.modelListPage(techniqueDetailService.getDetailsByTechniqueId(techniqueId), pageable);
    }
}
