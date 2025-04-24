package com.kora.koramonoapi.AnxietyBiometricData.domain.persistence;

import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.PhysiologicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhysiologicalDataRepository extends JpaRepository<PhysiologicalData,Integer> {

    Optional<PhysiologicalData> findById(Integer physiologicalDataId);

    List<PhysiologicalData> findByPatientId(Integer patientId);
    List<PhysiologicalData> findByPatientIdAndCreatedAtBetween(
            Integer patientId, LocalDateTime startOfMonth, LocalDateTime endOfMonth);
}
