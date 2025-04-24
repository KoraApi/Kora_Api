package com.kora.koramonoapi.ProgressManagement.service;

import com.kora.koramonoapi.ProgressManagement.domain.model.entity.EmotionalData;
import com.kora.koramonoapi.ProgressManagement.domain.persistence.EmotionalDataRepository;
import com.kora.koramonoapi.ProgressManagement.domain.service.EmotionalDataService;
import com.kora.koramonoapi.ProgressManagement.resource.CreateEmotionalDataResource;
import com.kora.koramonoapi.ProgressManagement.resource.UpdateEmotionalDataResource;
import com.kora.koramonoapi.Shared.Exception.ResourceNotFoundException;
import com.kora.koramonoapi.Shared.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class EmotionalDataImpl implements EmotionalDataService {
    private static final String ENTITY = "EmotionalData";
    private final EmotionalDataRepository emotionalDataRepository;
    private final Validator validator;

    public EmotionalDataImpl(EmotionalDataRepository emotionalDataRepository, Validator validator) {
        this.emotionalDataRepository = emotionalDataRepository;
        this.validator = validator;
    }

    @Override
    public List<EmotionalData> getAll() {
        return emotionalDataRepository.findAll();
    }

    @Override
    public Page<EmotionalData> getAll(Pageable pageable) {
        return emotionalDataRepository.findAll(pageable);
    }

    @Override
    public EmotionalData getById(Integer emotionalDataId) {
        return emotionalDataRepository.findById(emotionalDataId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, emotionalDataId));
    }

    @Override
    public List<EmotionalData> getByPatientId(Integer patientId) {
        List<EmotionalData> emotionalData = emotionalDataRepository.findByPatientId(patientId);

        if(emotionalData.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Emotional Data for this patient");

        return emotionalData;
    }

    @Override
    public EmotionalData create(String jwt, CreateEmotionalDataResource emotionalDataResource) {
        Set<ConstraintViolation<CreateEmotionalDataResource>> violations = validator.validate(emotionalDataResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        EmotionalData emotionalData = new EmotionalData();
        emotionalData.setPatientId(emotionalDataResource.getPatientId());
        emotionalData.setEmotion(emotionalDataResource.getEmotion());

        return emotionalDataRepository.save(emotionalData);
    }

    @Override
    public EmotionalData update(String jwt, Integer emotionalDataId, UpdateEmotionalDataResource request) {
        EmotionalData emotionalData = getById(emotionalDataId);

        if(emotionalData == null)
            throw new ResourceValidationException(ENTITY,
                    "Not found Emotional Data with ID:"+ emotionalDataId);

        if (request.getEmotion() != null) {
            emotionalData.setEmotion(request.getEmotion());
        }

        return emotionalDataRepository.save(emotionalData);
    }

    @Override
    public ResponseEntity<?> delete(String jwt, Integer emotionalDataId) {
        return emotionalDataRepository.findById(emotionalDataId).map(emotionalData -> {
            emotionalDataRepository.delete(emotionalData);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, emotionalDataId));
    }

    @Override
    public List<EmotionalData> getByPatientIdAndDateRange(Integer patientId, LocalDateTime startDate, LocalDateTime endDate) {
        return emotionalDataRepository.findByPatientIdAndDateRange(patientId, startDate, endDate);
    }
}
