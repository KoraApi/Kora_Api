package com.kora.koramonoapi.Profile.resource;

import com.kora.koramonoapi.IAM.domain.model.entity.Patient;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInformationResource {
    private Integer id;
    private Patient patient;
    private Integer age;
    private String gender;
    private String surgeryType;
    private String surgeryDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
