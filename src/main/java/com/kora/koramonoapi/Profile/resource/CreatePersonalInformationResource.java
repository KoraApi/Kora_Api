package com.kora.koramonoapi.Profile.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreatePersonalInformationResource {
    private Integer id;
    private Integer patientId;
    private Integer age;
    private String gender;
    private String surgeryType;
    private String surgeryDate;
}
