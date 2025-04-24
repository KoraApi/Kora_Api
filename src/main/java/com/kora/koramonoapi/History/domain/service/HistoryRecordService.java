package com.kora.koramonoapi.History.domain.service;

import com.kora.koramonoapi.History.domain.model.entity.HistoryRecord;
import com.kora.koramonoapi.History.resource.CreateHistoryRecordResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HistoryRecordService {
    List<HistoryRecord> getAll();
    Page<HistoryRecord> getAll(Pageable pageable);
    List<HistoryRecord> getByPatientId(Integer patientId);

    List<HistoryRecord> getByPatientIdAndDate(Integer patientId, int year, int month, int day);
    HistoryRecord create(String jwt, CreateHistoryRecordResource historyRecordResource);
    ResponseEntity<?> delete(String jwt, Integer historyRecordId);
}
