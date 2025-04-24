package com.kora.koramonoapi.History.service;

import com.kora.koramonoapi.History.domain.model.entity.FavoriteRecord;
import com.kora.koramonoapi.History.domain.persistence.FavoriteRecordRepository;
import com.kora.koramonoapi.History.domain.service.FavoriteRecordService;
import com.kora.koramonoapi.History.resource.CreateFavoriteRecordResource;
import com.kora.koramonoapi.Shared.Exception.ResourceNotFoundException;
import com.kora.koramonoapi.Shared.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class FavoriteRecordImpl implements FavoriteRecordService {
    private static final String ENTITY = "FavoriteRecord";
    private final FavoriteRecordRepository favoriteRecordRepository;
    private final Validator validator;


    public FavoriteRecordImpl(FavoriteRecordRepository favoriteRecordRepository, Validator validator) {
        this.favoriteRecordRepository = favoriteRecordRepository;
        this.validator = validator;
    }

    @Override
    public List<FavoriteRecord> getAll() {

        return favoriteRecordRepository.findAll();
    }

    @Override
    public Page<FavoriteRecord> getAll(Pageable pageable) {

        return favoriteRecordRepository.findAll(pageable);
    }


    @Override
    public List<FavoriteRecord> getByPatientId(Integer patientId) {
        List<FavoriteRecord> favoriteRecords = favoriteRecordRepository.findByPatientId(patientId);

        if(favoriteRecords.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Favorite Records for this patient");

        return favoriteRecords;
    }


    @Override
    public FavoriteRecord isFavorite(Integer patientId, Integer techniqueId) {
        return favoriteRecordRepository.findByPatientIdAndTechniqueId(patientId, techniqueId)
                .orElseThrow(() -> new ResourceNotFoundException("FavoriteRecord not found for patientId "
                        + patientId + " and techniqueId " + techniqueId));
    }


    @Override
    public FavoriteRecord create(String jwt, CreateFavoriteRecordResource favoriteRecordResource) {
        Set<ConstraintViolation<CreateFavoriteRecordResource>> violations = validator.validate(favoriteRecordResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        boolean exists = favoriteRecordRepository.existsByPatientIdAndTechniqueId(
                favoriteRecordResource.getPatientId(),
                favoriteRecordResource.getTechniqueId()
        );

        if (exists) {
            throw new ResourceValidationException(ENTITY, "This favorite record already exists.");
        }

        FavoriteRecord favoriteRecord = new FavoriteRecord();
        favoriteRecord.setPatientId(favoriteRecordResource.getPatientId());
        favoriteRecord.setTechniqueId(favoriteRecordResource.getTechniqueId());

        return favoriteRecordRepository.save(favoriteRecord);
    }

    @Override
    public ResponseEntity<?> delete(String jwt, Integer favoriteRecordId) {
        return favoriteRecordRepository.findById(favoriteRecordId).map(favoriteRecord -> {
            favoriteRecordRepository.delete(favoriteRecord);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, favoriteRecordId));
    }


}
