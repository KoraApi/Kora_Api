package com.kora.koramonoapi.History.domain.persistence;

import com.kora.koramonoapi.History.domain.model.entity.FavoriteRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRecordRepository extends JpaRepository<FavoriteRecord,Integer> {

    Optional<FavoriteRecord> findById(Integer favoriteRecordId);

    List<FavoriteRecord> findByPatientId(Integer patientId);

    boolean existsByPatientIdAndTechniqueId(Integer patientId, Integer techniqueId);

    Optional<FavoriteRecord> findByPatientIdAndTechniqueId(Integer patientId, Integer techniqueId);
}
