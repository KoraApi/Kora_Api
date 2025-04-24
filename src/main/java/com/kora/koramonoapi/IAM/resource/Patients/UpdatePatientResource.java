package com.kora.koramonoapi.IAM.resource.Patients;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePatientResource {
    private String fullName;
    private String email;
    private String photoUrl;
}
