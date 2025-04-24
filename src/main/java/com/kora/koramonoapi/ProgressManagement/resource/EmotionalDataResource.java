package com.kora.koramonoapi.ProgressManagement.resource;

import com.kora.koramonoapi.IAM.domain.model.entity.Patient;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class EmotionalDataResource {
    private Integer id;
    private Patient patient;
    private String emotion;
    private LocalDateTime createdAt;
}
