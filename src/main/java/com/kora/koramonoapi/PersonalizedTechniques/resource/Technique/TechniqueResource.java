package com.kora.koramonoapi.PersonalizedTechniques.resource.Technique;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class TechniqueResource {
    private Integer id;
    private String name;
    private String description;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
