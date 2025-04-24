package com.kora.koramonoapi.History.domain.persistence;

import com.kora.koramonoapi.History.domain.model.entity.HistoryRecord;
import com.kora.koramonoapi.Profile.domain.model.entity.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRecordRepository extends JpaRepository<HistoryRecord,Integer> {

    Optional<HistoryRecord> findById(Integer historyRecordId);

    List<HistoryRecord> findByPatientId(Integer patientId);

    List<HistoryRecord> findByPatientIdAndRegisteredAtBetween(
            Integer patientId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
