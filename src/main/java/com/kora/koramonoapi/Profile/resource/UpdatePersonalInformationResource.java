package com.kora.koramonoapi.Profile.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePersonalInformationResource {
    private Integer age;
    private String gender;
    private String surgeryType;
    private String surgeryDate;
}
