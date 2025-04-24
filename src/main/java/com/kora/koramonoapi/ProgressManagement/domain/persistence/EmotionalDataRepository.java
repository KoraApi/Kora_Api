package com.kora.koramonoapi.ProgressManagement.domain.persistence;

import com.kora.koramonoapi.ProgressManagement.domain.model.entity.EmotionalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmotionalDataRepository extends JpaRepository<EmotionalData,Integer> {
    Optional<EmotionalData> findById(Integer emotionalDataId);

    List<EmotionalData> findByPatientId(Integer patientId);

    // Nuevo método para encontrar registros en un rango de fechas
    @Query("SELECT e FROM EmotionalData e WHERE e.patientId = :patientId AND e.createdAt BETWEEN :startDate AND :endDate")
    List<EmotionalData> findByPatientIdAndDateRange(
            @Param("patientId") Integer patientId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

}
