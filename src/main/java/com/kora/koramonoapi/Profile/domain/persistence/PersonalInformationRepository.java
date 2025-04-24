package com.kora.koramonoapi.Profile.domain.persistence;

import com.kora.koramonoapi.Profile.domain.model.entity.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformation,Integer> {
    Optional<PersonalInformation> findById(Integer personalInformationId);

    List<PersonalInformation> findByPatientId(Integer patientId);
}
