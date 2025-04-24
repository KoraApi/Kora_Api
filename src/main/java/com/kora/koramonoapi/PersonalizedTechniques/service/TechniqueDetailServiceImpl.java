package com.kora.koramonoapi.PersonalizedTechniques.service;

import com.kora.koramonoapi.PersonalizedTechniques.domain.model.entity.TechniqueDetail;
import com.kora.koramonoapi.PersonalizedTechniques.domain.persistence.TechniqueDetailRepository;
import com.kora.koramonoapi.PersonalizedTechniques.domain.service.TechniqueDetailService;
import com.kora.koramonoapi.Shared.Exception.ResourceValidationException;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechniqueDetailServiceImpl implements TechniqueDetailService {
    private static final String ENTITY = "Technique";
    private final TechniqueDetailRepository techniqueDetailRepository;
    private final Validator validator;

    public TechniqueDetailServiceImpl(TechniqueDetailRepository techniqueDetailRepository, Validator validator) {
        this.techniqueDetailRepository = techniqueDetailRepository;
        this.validator = validator;
    }

    @Override
    public List<TechniqueDetail> getAll() {
        return techniqueDetailRepository.findAll();
    }

    @Override
    public Page<TechniqueDetail> getAll(Pageable pageable) {
        return techniqueDetailRepository.findAll(pageable);
    }

    @Override
    public List<TechniqueDetail> getDetailsByTechniqueId(Integer techniqueId) {
        List<TechniqueDetail> techniqueDetails = techniqueDetailRepository.findByTechniqueId(techniqueId);

        if(techniqueDetails.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found details for this technique");

        return techniqueDetails;
    }
}
