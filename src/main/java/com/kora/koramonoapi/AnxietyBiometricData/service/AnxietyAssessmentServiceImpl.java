package com.kora.koramonoapi.AnxietyBiometricData.service;

import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.AnxietyAssessment;
import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.PhysiologicalData;
import com.kora.koramonoapi.AnxietyBiometricData.domain.persistence.AnxietyAssessmentRepository;
import com.kora.koramonoapi.AnxietyBiometricData.domain.service.AnxietyAssessmentService;
import com.kora.koramonoapi.AnxietyBiometricData.resource.AnxietyAssessment.CreateAnxietyAssessmentResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.AnxietyAssessment.UpdateAnxietyAssessmentResource;
import com.kora.koramonoapi.Shared.Exception.ResourceNotFoundException;
import com.kora.koramonoapi.Shared.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Service
public class AnxietyAssessmentServiceImpl  implements AnxietyAssessmentService {

    private static final String ENTITY = "AnxietyAssessment";
    private final AnxietyAssessmentRepository anxietyAssessmentRepository;
    private final Validator validator;

    public AnxietyAssessmentServiceImpl(AnxietyAssessmentRepository anxietyAssessmentRepository, Validator validator) {
        this.anxietyAssessmentRepository = anxietyAssessmentRepository;
        this.validator = validator;
    }


    @Override
    public List<AnxietyAssessment> getAll() {
        return anxietyAssessmentRepository.findAll();
    }

    @Override
    public Page<AnxietyAssessment> getAll(Pageable pageable) {
        return anxietyAssessmentRepository.findAll(pageable);
    }

    @Override
    public AnxietyAssessment getById(Integer anxietyAssessmentId) {
        return anxietyAssessmentRepository.findById(anxietyAssessmentId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, anxietyAssessmentId));
    }

    @Override
    public List<AnxietyAssessment> getByPatientId(Integer patientId) {
        List<AnxietyAssessment> anxietyAssessments = anxietyAssessmentRepository.findByPatientId(patientId);

        if(anxietyAssessments.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Anxiety Assessment for this patient");

        return anxietyAssessments;
    }

    @Override
    public List<AnxietyAssessment> getByPatientIdAndDate(Integer patientId, int year, int month) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDateTime startOfMonth = firstDayOfMonth.atStartOfDay();
        LocalDateTime endOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth()).atTime(LocalTime.MAX); // YYYY-MM-<last day> 23:59:59

        List<AnxietyAssessment> data = anxietyAssessmentRepository.findByPatientIdAndCreatedAtBetween(
                patientId, startOfMonth, endOfMonth);

        if (data.isEmpty()) {
            throw new ResourceValidationException(ENTITY, "No anxiety data found for this month.");
        }

        return data;
    }

    @Override
    public AnxietyAssessment create(String jwt, CreateAnxietyAssessmentResource anxietyAssessmentResource) {
        Set<ConstraintViolation<CreateAnxietyAssessmentResource>> violations = validator.validate(anxietyAssessmentResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        //Patient patient = externalConfiguration.getPatient(jwt);
        AnxietyAssessment anxietyAssessment = new AnxietyAssessment();
        //anxietyAssessment.setPatientId(patient.getId());
        anxietyAssessment.setPatientId(anxietyAssessmentResource.getPatientId());
        anxietyAssessment.setStaiScore(anxietyAssessmentResource.getStaiScore());

        return anxietyAssessmentRepository.save(anxietyAssessment);
    }

    @Override
    public AnxietyAssessment update(String jwt, Integer anxietyAssessmentId, UpdateAnxietyAssessmentResource request) {
        AnxietyAssessment anxietyAssessment = getById(anxietyAssessmentId);

        if(anxietyAssessment == null)
            throw new ResourceValidationException(ENTITY,
                    "Not found Anxiety Assessment with ID:"+ anxietyAssessmentId);

        if (request.getStaiScore() != null) {
            anxietyAssessment.setStaiScore(request.getStaiScore());
        }
        return anxietyAssessmentRepository.save(anxietyAssessment);
    }

    @Override
    public ResponseEntity<?> delete(String jwt, Integer anxietyAssessmentId) {
        return anxietyAssessmentRepository.findById(anxietyAssessmentId).map(anxietyAssessment -> {
            anxietyAssessmentRepository.delete(anxietyAssessment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, anxietyAssessmentId));
    }
}
