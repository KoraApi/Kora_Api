package com.kora.koramonoapi.IAM.resource.Patients;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientResource {
    private Integer id;
    private String fullName;
    private String email;
    private String photoUrl;
}
