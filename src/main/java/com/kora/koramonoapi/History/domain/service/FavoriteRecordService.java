package com.kora.koramonoapi.History.domain.service;

import com.kora.koramonoapi.History.domain.model.entity.FavoriteRecord;
import com.kora.koramonoapi.History.resource.CreateFavoriteRecordResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FavoriteRecordService {
    List<FavoriteRecord> getAll();
    Page<FavoriteRecord> getAll(Pageable pageable);
    List<FavoriteRecord> getByPatientId(Integer patientId);
    FavoriteRecord isFavorite(Integer patientId, Integer techniqueId);
    FavoriteRecord create(String jwt, CreateFavoriteRecordResource favoriteRecordResource);
    ResponseEntity<?> delete(String jwt, Integer favoriteRecordId);
}
