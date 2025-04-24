package com.kora.koramonoapi.PersonalizedTechniques.service;

import com.kora.koramonoapi.PersonalizedTechniques.domain.model.entity.Technique;
import com.kora.koramonoapi.PersonalizedTechniques.domain.persistence.TechniqueRepository;
import com.kora.koramonoapi.PersonalizedTechniques.domain.service.TechniqueService;
import com.kora.koramonoapi.Shared.Exception.ResourceNotFoundException;
import com.kora.koramonoapi.Shared.Exception.ResourceValidationException;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechniqueServiceImpl implements TechniqueService {
    private static final String ENTITY = "Technique";
    private final TechniqueRepository techniqueRepository;
    private final Validator validator;

    public TechniqueServiceImpl(TechniqueRepository techniqueRepository, Validator validator) {
        this.techniqueRepository = techniqueRepository;
        this.validator = validator;
    }

    @Override
    public List<Technique> getAll() {
        return techniqueRepository.findAll();
    }

    @Override
    public Page<Technique> getAll(Pageable pageable) {
        return techniqueRepository.findAll(pageable);
    }

    @Override
    public Technique getById(Integer techniqueId) {
        return techniqueRepository.findById(techniqueId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, techniqueId));
    }

     @Override
    public List<Technique> getByCategory(String type) {
         List<Technique> techniques = techniqueRepository.findByType(type);

         if(techniques.isEmpty())
             throw new ResourceValidationException(ENTITY,
                     "Not found techniques for this category");

         return techniques;
    }
}
