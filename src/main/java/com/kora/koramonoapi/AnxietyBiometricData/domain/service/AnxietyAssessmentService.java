package com.kora.koramonoapi.AnxietyBiometricData.domain.service;

import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.AnxietyAssessment;
import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.PhysiologicalData;
import com.kora.koramonoapi.AnxietyBiometricData.resource.AnxietyAssessment.CreateAnxietyAssessmentResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.AnxietyAssessment.UpdateAnxietyAssessmentResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnxietyAssessmentService {

    List<AnxietyAssessment> getAll();
    Page<AnxietyAssessment> getAll(Pageable pageable);
    AnxietyAssessment getById(Integer anxietyAssessmentId);

    List<AnxietyAssessment> getByPatientId(Integer patientId);

    List<AnxietyAssessment> getByPatientIdAndDate(Integer patientId, int year, int month);
    AnxietyAssessment create(String jwt, CreateAnxietyAssessmentResource anxietyAssessmentResource);
    AnxietyAssessment update(String jwt, Integer anxietyAssessmentId, UpdateAnxietyAssessmentResource request);
    ResponseEntity<?> delete(String jwt, Integer anxietyAssessmentId);
}
