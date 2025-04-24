package com.kora.koramonoapi.History.service;

import com.kora.koramonoapi.History.domain.model.entity.HistoryRecord;
import com.kora.koramonoapi.History.domain.persistence.HistoryRecordRepository;
import com.kora.koramonoapi.History.domain.service.HistoryRecordService;
import com.kora.koramonoapi.History.resource.CreateHistoryRecordResource;
import com.kora.koramonoapi.Shared.Exception.ResourceNotFoundException;
import com.kora.koramonoapi.Shared.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Service
public class HistoryRecordImpl implements HistoryRecordService {
    private static final String ENTITY = "HistoryRecord";
    private final HistoryRecordRepository historyRecordRepository;
    private final Validator validator;


    public HistoryRecordImpl(HistoryRecordRepository historyRecordRepository, Validator validator) {
        this.historyRecordRepository = historyRecordRepository;
        this.validator = validator;
    }

    @Override
    public List<HistoryRecord> getAll() {

        return historyRecordRepository.findAll();
    }

    @Override
    public Page<HistoryRecord> getAll(Pageable pageable) {

        return historyRecordRepository.findAll(pageable);
    }


    @Override
    public List<HistoryRecord> getByPatientId(Integer patientId) {
        List<HistoryRecord> historyRecords = historyRecordRepository.findByPatientId(patientId);

        if(historyRecords.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found History Records for this patient");

        return historyRecords;
    }

    @Override
    public List<HistoryRecord> getByPatientIdAndDate(Integer patientId, int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        LocalDateTime startOfDay = date.atStartOfDay(); // YYYY-MM-DD 00:00:00
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX); // YYYY-MM-DD 23:59:59

        List<HistoryRecord> historyRecords = historyRecordRepository.findByPatientIdAndRegisteredAtBetween(
                patientId, startOfDay, endOfDay);

        if (historyRecords.isEmpty()) {
            throw new ResourceValidationException(ENTITY, "No history records found for this date.");
        }

        return historyRecords;
    }

    @Override
    public HistoryRecord create(String jwt, CreateHistoryRecordResource historyRecordResource) {
        Set<ConstraintViolation<CreateHistoryRecordResource>> violations = validator.validate(historyRecordResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setPatientId(historyRecordResource.getPatientId());
        historyRecord.setTechniqueId(historyRecordResource.getTechniqueId());
        historyRecord.setEmotion(historyRecordResource.getEmotion());
        historyRecord.setRegisteredAt(LocalDateTime.now());

        return historyRecordRepository.save(historyRecord);
    }

    @Override
    public ResponseEntity<?> delete(String jwt, Integer historyRecordId) {
        return historyRecordRepository.findById(historyRecordId).map(historyRecord -> {
            historyRecordRepository.delete(historyRecord);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, historyRecordId));
    }


}
