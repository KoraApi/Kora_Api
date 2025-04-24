package com.kora.koramonoapi.IAM.mapping;

import com.kora.koramonoapi.IAM.domain.model.entity.Patient;
import com.kora.koramonoapi.IAM.resource.Patients.CreatePatientResource;
import com.kora.koramonoapi.IAM.resource.Patients.PatientResource;
import com.kora.koramonoapi.IAM.resource.Patients.UpdatePatientResource;
import com.kora.koramonoapi.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PatientMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public PatientResource toResource(Patient model) {
        return mapper.map(model, PatientResource.class);
    }

    public Patient toModel(CreatePatientResource resource) {
        return mapper.map(resource, Patient.class);
    }

    public Patient toModel(UpdatePatientResource resource) {
        return mapper.map(resource, Patient.class);
    }

    public Page<PatientResource> modelListPage(List<Patient> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, PatientResource.class), pageable, modelList.size());
    }
}
