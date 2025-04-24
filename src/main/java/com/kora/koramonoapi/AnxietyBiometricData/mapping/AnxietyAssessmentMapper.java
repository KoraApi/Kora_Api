package com.kora.koramonoapi.AnxietyBiometricData.mapping;

import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.AnxietyAssessment;
import com.kora.koramonoapi.AnxietyBiometricData.resource.AnxietyAssessment.AnxietyAssessmentResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.AnxietyAssessment.CreateAnxietyAssessmentResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.AnxietyAssessment.UpdateAnxietyAssessmentResource;
import com.kora.koramonoapi.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class AnxietyAssessmentMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public AnxietyAssessmentResource toResource(AnxietyAssessment model) {
        return mapper.map(model, AnxietyAssessmentResource.class);
    }

    public AnxietyAssessment toModel(CreateAnxietyAssessmentResource resource) {
        return mapper.map(resource, AnxietyAssessment.class);
    }

    public AnxietyAssessment toModel(UpdateAnxietyAssessmentResource resource) {
        return mapper.map(resource, AnxietyAssessment.class);
    }

    public Page<AnxietyAssessmentResource> modelListPage(List<AnxietyAssessment> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, AnxietyAssessmentResource.class), pageable, modelList.size());
    }
}
