package com.kora.koramonoapi.AnxietyBiometricData.service;

import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.PhysiologicalData;
import com.kora.koramonoapi.AnxietyBiometricData.domain.persistence.PhysiologicalDataRepository;
import com.kora.koramonoapi.AnxietyBiometricData.domain.service.PhysiologicalDataService;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.CreatePhysiologicalDataResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.UpdatePhysiologicalDataResource;
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
public class PhysiologicalDataServiceImpl implements PhysiologicalDataService {

    private static final String ENTITY = "PhysiologicalData";
    private final PhysiologicalDataRepository physiologicalDataRepository;
    private final Validator validator;


    public PhysiologicalDataServiceImpl(PhysiologicalDataRepository physiologicalDataRepository, Validator validator) {
        this.physiologicalDataRepository = physiologicalDataRepository;
        this.validator = validator;
    }


    @Override
    public List<PhysiologicalData> getAll() {
        return physiologicalDataRepository.findAll();
    }

    @Override
    public Page<PhysiologicalData> getAll(Pageable pageable) {
        return physiologicalDataRepository.findAll(pageable);
    }

    @Override
    public PhysiologicalData getById(Integer physiologicalDataId) {
        return physiologicalDataRepository.findById(physiologicalDataId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, physiologicalDataId));
    }

    @Override
    public List<PhysiologicalData> getByPatientId(Integer patientId) {
        List<PhysiologicalData> physiologicalData = physiologicalDataRepository.findByPatientId(patientId);

        if(physiologicalData.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Physiological Data for this patient");

        return physiologicalData;
    }

    @Override
    public List<PhysiologicalData> getByPatientIdAndDate(Integer patientId, int year, int month) {
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDateTime startOfMonth = firstDayOfMonth.atStartOfDay();
        LocalDateTime endOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth()).atTime(LocalTime.MAX); // YYYY-MM-<last day> 23:59:59

        List<PhysiologicalData> data = physiologicalDataRepository.findByPatientIdAndCreatedAtBetween(
                patientId, startOfMonth, endOfMonth);

        if (data.isEmpty()) {
            throw new ResourceValidationException(ENTITY, "No physiological data found for this month.");
        }

        return data;
    }

    @Override
    public PhysiologicalData create(String jwt, CreatePhysiologicalDataResource physiologicalDataResource) {
        Set<ConstraintViolation<CreatePhysiologicalDataResource>> violations = validator.validate(physiologicalDataResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        //Patient patient = externalConfiguration.getPatient(jwt);
        PhysiologicalData physiologicalData = new PhysiologicalData();
        //physiologicalData.setPatientId(patient.getId());
        physiologicalData.setPatientId(physiologicalDataResource.getPatientId());
        physiologicalData.setHeartRate(physiologicalDataResource.getHeartRate());
        physiologicalData.setBloodOxygen(physiologicalDataResource.getBloodOxygen());
        physiologicalData.setSleepHours(physiologicalDataResource.getSleepHours());

        return physiologicalDataRepository.save(physiologicalData);
    }

    @Override
    public PhysiologicalData update(String jwt, Integer physiologicalDataId, UpdatePhysiologicalDataResource request) {
        PhysiologicalData physiologicalData = getById(physiologicalDataId);

        if(physiologicalData == null)
            throw new ResourceValidationException(ENTITY,
                    "Not found Physiological Data with ID:"+ physiologicalDataId);

        if (request.getHeartRate() != null) {
            physiologicalData.setHeartRate(request.getHeartRate());
        }
        if (request.getBloodOxygen() != null) {
            physiologicalData.setBloodOxygen(request.getBloodOxygen());
        }
        if (request.getSleepHours() != null) {
            physiologicalData.setSleepHours(request.getSleepHours());
        }
        return physiologicalDataRepository.save(physiologicalData);
    }

    @Override
    public ResponseEntity<?> delete(String jwt, Integer physiologicalDataId) {
        return physiologicalDataRepository.findById(physiologicalDataId).map(physiologicalData -> {
            physiologicalDataRepository.delete(physiologicalData);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, physiologicalDataId));
    }
}
