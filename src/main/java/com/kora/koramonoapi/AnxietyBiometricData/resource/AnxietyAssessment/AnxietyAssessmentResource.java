package com.kora.koramonoapi.AnxietyBiometricData.resource.AnxietyAssessment;

import com.kora.koramonoapi.IAM.domain.model.entity.Patient;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class AnxietyAssessmentResource {
    private Integer id;
    private Patient patient;
    private Integer staiScore;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
