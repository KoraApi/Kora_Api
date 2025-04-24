package com.kora.koramonoapi.PersonalizedTechniques.mapping;

import com.kora.koramonoapi.PersonalizedTechniques.domain.model.entity.TechniqueDetail;
import com.kora.koramonoapi.PersonalizedTechniques.resource.TechniqueDetail.TechniqueDetailResource;
import com.kora.koramonoapi.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TechniqueDetailMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;
    public TechniqueDetailResource toResource(TechniqueDetail model) {
        return mapper.map(model, TechniqueDetailResource.class);
    }

    public Page<TechniqueDetailResource> modelListPage(List<TechniqueDetail> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TechniqueDetailResource.class), pageable, modelList.size());
    }
}
