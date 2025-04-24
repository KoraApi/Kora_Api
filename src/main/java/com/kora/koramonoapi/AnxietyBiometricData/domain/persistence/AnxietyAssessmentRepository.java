package com.kora.koramonoapi.AnxietyBiometricData.domain.persistence;

import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.AnxietyAssessment;
import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.PhysiologicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnxietyAssessmentRepository extends JpaRepository<AnxietyAssessment,Integer> {

    Optional<AnxietyAssessment> findById(Integer anxietyAssessmentId);

    List<AnxietyAssessment> findByPatientId(Integer patientId);

    List<AnxietyAssessment> findByPatientIdAndCreatedAtBetween(
            Integer patientId, LocalDateTime startOfMonth, LocalDateTime endOfMonth);
}
