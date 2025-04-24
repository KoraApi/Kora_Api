package com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData;

import com.kora.koramonoapi.IAM.domain.model.entity.Patient;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PhysiologicalDataResource {
    private Integer id;
    private Patient patient;
    private Integer heartRate;
    private Integer bloodOxygen;
    private Integer sleepHours;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
