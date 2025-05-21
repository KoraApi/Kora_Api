package com.kora.koramonoapi.AnxietyBiometricData.resource.SelfPerception;

import com.kora.koramonoapi.IAM.domain.model.entity.Patient;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class SelfPerceptionResource {
    private Integer id;
    private Patient patient;
    private String emotion;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}



