package com.kora.koramonoapi.PersonalizedTechniques.domain.service;

import com.kora.koramonoapi.PersonalizedTechniques.domain.model.entity.TechniqueDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TechniqueDetailService {
    List<TechniqueDetail> getAll();
    Page<TechniqueDetail> getAll(Pageable pageable);
    List<TechniqueDetail> getDetailsByTechniqueId(Integer techniqueId);
}
