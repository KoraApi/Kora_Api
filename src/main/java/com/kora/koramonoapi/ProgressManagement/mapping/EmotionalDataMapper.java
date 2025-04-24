package com.kora.koramonoapi.ProgressManagement.mapping;

import com.kora.koramonoapi.ProgressManagement.domain.model.entity.EmotionalData;
import com.kora.koramonoapi.ProgressManagement.resource.CreateEmotionalDataResource;
import com.kora.koramonoapi.ProgressManagement.resource.EmotionalDataResource;
import com.kora.koramonoapi.ProgressManagement.resource.UpdateEmotionalDataResource;
import com.kora.koramonoapi.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class EmotionalDataMapper  implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public EmotionalDataResource toResource(EmotionalData model) {
        return mapper.map(model, EmotionalDataResource.class);
    }

    public EmotionalData toModel(CreateEmotionalDataResource resource) {
        return mapper.map(resource, EmotionalData.class);
    }

    public EmotionalData toModel(UpdateEmotionalDataResource resource) {
        return mapper.map(resource, EmotionalData.class);
    }

    public Page<EmotionalDataResource> modelListPage(List<EmotionalData> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, EmotionalDataResource.class), pageable, modelList.size());
    }
}
