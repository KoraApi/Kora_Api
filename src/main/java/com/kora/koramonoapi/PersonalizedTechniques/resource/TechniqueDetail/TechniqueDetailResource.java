package com.kora.koramonoapi.PersonalizedTechniques.resource.TechniqueDetail;

import com.kora.koramonoapi.PersonalizedTechniques.domain.model.entity.Technique;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@With
@NoArgsConstructor
@AllArgsConstructor
public class TechniqueDetailResource {
    private Integer id;
    private Technique technique;
    private String url; // Only for music therapy and mindfulness
    private Integer stepNumber; // Only for breathing techniques
    private String instruction;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
