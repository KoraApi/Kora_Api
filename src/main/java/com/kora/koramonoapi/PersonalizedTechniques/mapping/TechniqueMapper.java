package com.kora.koramonoapi.PersonalizedTechniques.mapping;

import com.kora.koramonoapi.PersonalizedTechniques.domain.model.entity.Technique;
import com.kora.koramonoapi.PersonalizedTechniques.resource.Technique.TechniqueResource;
import com.kora.koramonoapi.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TechniqueMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public TechniqueResource toResource(Technique model) {
        return mapper.map(model, TechniqueResource.class);
    }
    public Page<TechniqueResource> modelListPage(List<Technique> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TechniqueResource.class), pageable, modelList.size());
    }



}
