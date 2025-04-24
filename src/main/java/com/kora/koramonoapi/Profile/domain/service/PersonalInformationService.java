package com.kora.koramonoapi.Profile.domain.service;

import com.kora.koramonoapi.Profile.domain.model.entity.PersonalInformation;
import com.kora.koramonoapi.Profile.resource.CreatePersonalInformationResource;
import com.kora.koramonoapi.Profile.resource.UpdatePersonalInformationResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonalInformationService {
    List<PersonalInformation> getAll();
    Page<PersonalInformation> getAll(Pageable pageable);
    PersonalInformation getById(Integer personalInformationId);
    List<PersonalInformation> getByPatientId(Integer patientId);
    PersonalInformation create(String jwt, CreatePersonalInformationResource personalInformationResource);
    PersonalInformation update(String jwt, Integer personalInformationId, UpdatePersonalInformationResource request);
    ResponseEntity<?> delete(String jwt, Integer personalInformationId);
}
