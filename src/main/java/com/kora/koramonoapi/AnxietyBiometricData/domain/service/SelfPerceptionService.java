package com.kora.koramonoapi.AnxietyBiometricData.domain.service;

import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.PhysiologicalData;
import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.SelfPerception;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.CreatePhysiologicalDataResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.UpdatePhysiologicalDataResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.SelfPerception.CreateSelfPerceptionResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.SelfPerception.UpdateSelfPerceptionResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SelfPerceptionService {
    List<SelfPerception> getAll();
    Page<SelfPerception> getAll(Pageable pageable);
    SelfPerception getById(Integer selfPerceptionId);
    List<SelfPerception> getByPatientId(Integer patientId);
    List<SelfPerception> getByPatientIdAndDate(Integer patientId, int year, int month);
    SelfPerception create(String jwt, CreateSelfPerceptionResource selfPerceptionResource);
    SelfPerception update(String jwt, Integer selfPerceptionId, UpdateSelfPerceptionResource request);
    ResponseEntity<?> delete(String jwt, Integer selfPerceptionId);
}

