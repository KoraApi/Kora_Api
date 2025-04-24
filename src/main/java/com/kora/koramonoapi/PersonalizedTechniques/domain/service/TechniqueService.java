package com.kora.koramonoapi.PersonalizedTechniques.domain.service;

import com.kora.koramonoapi.PersonalizedTechniques.domain.model.entity.Technique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TechniqueService {
    List<Technique> getAll();
    Page<Technique> getAll(Pageable pageable);
    Technique getById(Integer techniqueId);
    List<Technique> getByCategory(String category);
}
