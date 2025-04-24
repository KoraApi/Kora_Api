package com.kora.koramonoapi.PersonalizedTechniques.domain.persistence;

import com.kora.koramonoapi.PersonalizedTechniques.domain.model.entity.TechniqueDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TechniqueDetailRepository extends JpaRepository<TechniqueDetail, Integer> {
    List<TechniqueDetail> findByTechniqueId(Integer techniqueId);

}
