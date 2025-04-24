package com.kora.koramonoapi.IAM.domain.service;

import com.kora.koramonoapi.IAM.domain.model.entity.Patient;
import com.kora.koramonoapi.IAM.resource.Auth.LoginRequestResource;
import com.kora.koramonoapi.IAM.resource.Patients.CreatePatientResource;
import com.kora.koramonoapi.IAM.resource.Patients.UpdatePatientResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {
    List<Patient> getAll();
    Page<Patient> getAll(Pageable pageable);
    Patient getById(Integer patientId);
    Patient login(LoginRequestResource resource);
    Patient create(String jwt, CreatePatientResource patientResource);
    Patient update(String jwt, Integer patientId, UpdatePatientResource request);
    ResponseEntity<?> delete(String jwt, Integer patientId);

}
