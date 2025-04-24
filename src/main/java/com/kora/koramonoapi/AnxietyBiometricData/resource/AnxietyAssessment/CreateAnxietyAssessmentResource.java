package com.kora.koramonoapi.AnxietyBiometricData.resource.AnxietyAssessment;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateAnxietyAssessmentResource {
    private Integer id;
    private Integer patientId;
    private Integer staiScore;
}
