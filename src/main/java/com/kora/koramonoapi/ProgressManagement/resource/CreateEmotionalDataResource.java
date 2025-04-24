package com.kora.koramonoapi.ProgressManagement.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmotionalDataResource {
    private Integer id;

    @NotNull
    private Integer patientId;

    @NotBlank
    private String emotion;
}
