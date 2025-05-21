package com.kora.koramonoapi.AnxietyBiometricData.service;

import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.PhysiologicalData;
import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.SelfPerception;
import com.kora.koramonoapi.AnxietyBiometricData.domain.persistence.PhysiologicalDataRepository;
import com.kora.koramonoapi.AnxietyBiometricData.domain.persistence.SelfPerceptionRepository;
import com.kora.koramonoapi.AnxietyBiometricData.domain.service.PhysiologicalDataService;
import com.kora.koramonoapi.AnxietyBiometricData.domain.service.SelfPerceptionService;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.CreatePhysiologicalDataResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.SelfPerception.CreateSelfPerceptionResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.SelfPerception.UpdateSelfPerceptionResource;
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
public class SelfPerceptionServiceImpl implements SelfPerceptionService {
    private static final String ENTITY = "SelfPerception";
    private final SelfPerceptionRepository selfPerceptionRepository;
    private final Validator validator;


    public SelfPerceptionServiceImpl(SelfPerceptionRepository selfPerceptionRepository, Validator validator) {
        this.selfPerceptionRepository = selfPerceptionRepository;
        this.validator = validator;
    }

    @Override
    public List<SelfPerception> getAll() {
        return selfPerceptionRepository.findAll();
    }

    @Override
    public Page<SelfPerception> getAll(Pageable pageable) {
        return selfPerceptionRepository.findAll(pageable);
    }

    @Override
    public SelfPerception getById(Integer selfPerceptionId) {
        return selfPerceptionRepository.findById(selfPerceptionId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, selfPerceptionId));
    }

    @Override
    public List<SelfPerception> getByPatientId(Integer patientId) {
        List<SelfPerception> selfPerceptions = selfPerceptionRepository.findByPatientId(patientId);

        if(selfPerceptions.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Self Perceptions Data for this patient");

        return selfPerceptions;
    }

    @Override
    public List<SelfPerception> getByPatientIdAndDate(Integer patientId, int year, int month) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDateTime startOfMonth = firstDayOfMonth.atStartOfDay();
        LocalDateTime endOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth()).atTime(LocalTime.MAX);

        List<SelfPerception> data = selfPerceptionRepository.findByPatientIdAndCreatedAtBetween(
                patientId, startOfMonth, endOfMonth);

        if (data.isEmpty()) {
            throw new ResourceValidationException(ENTITY, "No Self Perceptions Data found for this month.");
        }

        return data;
    }

    @Override
    public SelfPerception create(String jwt, CreateSelfPerceptionResource selfPerceptionResource) {
        Set<ConstraintViolation<CreateSelfPerceptionResource>> violations = validator.validate(selfPerceptionResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        SelfPerception selfPerception = new SelfPerception();
        selfPerception.setPatientId(selfPerceptionResource.getPatientId());
        selfPerception.setEmotion(selfPerceptionResource.getEmotion());

        return selfPerceptionRepository.save(selfPerception);
    }

    @Override
    public SelfPerception update(String jwt, Integer selfPerceptionId, UpdateSelfPerceptionResource request) {
        SelfPerception selfPerception = getById(selfPerceptionId);

        if(selfPerception == null)
            throw new ResourceValidationException(ENTITY,
                    "Not found Self Perception with ID:"+ selfPerceptionId);

        if (request.getEmotion() != null) {
            selfPerception.setEmotion(request.getEmotion());
        }
        return selfPerceptionRepository.save(selfPerception);
    }

    @Override
    public ResponseEntity<?> delete(String jwt, Integer selfPerceptionId) {
        return selfPerceptionRepository.findById(selfPerceptionId).map(selfPerception -> {
            selfPerceptionRepository.delete(selfPerception);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, selfPerceptionId));
    }
}
