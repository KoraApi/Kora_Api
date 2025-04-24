package com.kora.koramonoapi.Profile.service;

import com.kora.koramonoapi.Profile.domain.model.entity.PersonalInformation;
import com.kora.koramonoapi.Profile.domain.persistence.PersonalInformationRepository;
import com.kora.koramonoapi.Profile.domain.service.PersonalInformationService;
import com.kora.koramonoapi.Profile.resource.CreatePersonalInformationResource;
import com.kora.koramonoapi.Profile.resource.UpdatePersonalInformationResource;
import com.kora.koramonoapi.Shared.Exception.ResourceNotFoundException;
import com.kora.koramonoapi.Shared.Exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PersonalInformationImpl implements PersonalInformationService {
    private static final String ENTITY = "PersonalInformation";
    private final PersonalInformationRepository personalInformationRepository;
    private final Validator validator;


    public PersonalInformationImpl(PersonalInformationRepository personalInformationRepository, Validator validator) {
        this.personalInformationRepository = personalInformationRepository;
        this.validator = validator;
    }

    @Override
    public List<PersonalInformation> getAll() {

        return personalInformationRepository.findAll();
    }

    @Override
    public Page<PersonalInformation> getAll(Pageable pageable) {

        return personalInformationRepository.findAll(pageable);
    }

    @Override
    public PersonalInformation getById(Integer personalInformationId) {
        return personalInformationRepository.findById(personalInformationId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, personalInformationId));
    }

    @Override
    public List<PersonalInformation> getByPatientId(Integer patientId) {
        List<PersonalInformation> personalInformations = personalInformationRepository.findByPatientId(patientId);

        if(personalInformations.isEmpty())
            throw new ResourceValidationException(ENTITY,
                    "Not found Personal Information for this patient");

        return personalInformations;
    }

    @Override
    public PersonalInformation create(String jwt, CreatePersonalInformationResource personalInformationResource) {
        Set<ConstraintViolation<CreatePersonalInformationResource>> violations = validator.validate(personalInformationResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setPatientId(personalInformationResource.getPatientId());
        personalInformation.setAge(personalInformationResource.getAge());
        personalInformation.setGender(personalInformationResource.getGender());
        personalInformation.setSurgeryType(personalInformationResource.getSurgeryType());
        personalInformation.setSurgeryDate(personalInformationResource.getSurgeryDate());

        return personalInformationRepository.save(personalInformation);
    }

    @Override
    public PersonalInformation update(String jwt, Integer personalInformationId, UpdatePersonalInformationResource request) {
        PersonalInformation personalInformation = getById(personalInformationId);

        if(personalInformation == null)
            throw new ResourceValidationException(ENTITY,
                    "Not found Personal Information with ID:"+ personalInformationId);

        if (request.getAge() != null) {
            personalInformation.setAge(request.getAge());
        }
        if (request.getGender() != null) {
            personalInformation.setGender(request.getGender());
        }
        if (request.getSurgeryType() != null) {
            personalInformation.setSurgeryType(request.getSurgeryType());
        }
        if (request.getSurgeryDate() != null) {
            personalInformation.setSurgeryDate(request.getSurgeryDate());
        }
        return personalInformationRepository.save(personalInformation);

    }

    @Override
    public ResponseEntity<?> delete(String jwt, Integer personalInformationId) {
        return personalInformationRepository.findById(personalInformationId).map(personalInformation -> {
            personalInformationRepository.delete(personalInformation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, personalInformationId));
    }
}
