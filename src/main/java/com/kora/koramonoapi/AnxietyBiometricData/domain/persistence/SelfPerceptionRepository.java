package com.kora.koramonoapi.AnxietyBiometricData.domain.persistence;

import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.PhysiologicalData;
import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.SelfPerception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface SelfPerceptionRepository extends JpaRepository<SelfPerception,Integer>{
    Optional<SelfPerception> findById(Integer selfPerceptionId);
    List<SelfPerception> findByPatientId(Integer patientId);
    List<SelfPerception> findByPatientIdAndCreatedAtBetween(
            Integer patientId, LocalDateTime startOfMonth, LocalDateTime endOfMonth);
}

