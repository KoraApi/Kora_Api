package com.kora.koramonoapi.ProgressManagement.domain.service;

import com.kora.koramonoapi.ProgressManagement.domain.model.entity.EmotionalData;
import com.kora.koramonoapi.ProgressManagement.resource.CreateEmotionalDataResource;
import com.kora.koramonoapi.ProgressManagement.resource.UpdateEmotionalDataResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface EmotionalDataService {
    List<EmotionalData> getAll();
    Page<EmotionalData> getAll(Pageable pageable);
    EmotionalData getById(Integer emotionalDataId);
    List<EmotionalData> getByPatientId(Integer patientId);
    EmotionalData create(String jwt, CreateEmotionalDataResource emotionalDataResource);
    EmotionalData update(String jwt, Integer emotionalDataId, UpdateEmotionalDataResource request);
    ResponseEntity<?> delete(String jwt, Integer emotionalDataId);
    List<EmotionalData> getByPatientIdAndDateRange(Integer patientId, LocalDateTime startDate, LocalDateTime endDate);
}