package com.kora.koramonoapi.AnxietyBiometricData.resource.SelfPerception;

import lombok.*;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateSelfPerceptionResource {
    private Integer id;
    private Integer patientId;
    private String emotion;
}