package com.kora.koramonoapi.PersonalizedTechniques.domain.persistence;

import com.kora.koramonoapi.PersonalizedTechniques.domain.model.entity.Technique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechniqueRepository extends JpaRepository<Technique, Integer> {
    List<Technique> findByType(String type);
}
