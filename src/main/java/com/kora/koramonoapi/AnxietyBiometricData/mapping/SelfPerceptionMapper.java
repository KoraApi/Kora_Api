package com.kora.koramonoapi.AnxietyBiometricData.mapping;

import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.PhysiologicalData;
import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.SelfPerception;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.CreatePhysiologicalDataResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.PhysiologicalDataResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.UpdatePhysiologicalDataResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.SelfPerception.CreateSelfPerceptionResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.SelfPerception.SelfPerceptionResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.SelfPerception.UpdateSelfPerceptionResource;
import com.kora.koramonoapi.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class SelfPerceptionMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public SelfPerceptionResource toResource(SelfPerception model) {
        return mapper.map(model, SelfPerceptionResource.class);
    }

    public SelfPerception toModel(CreateSelfPerceptionResource resource) {
        return mapper.map(resource, SelfPerception.class);
    }

    public SelfPerception toModel(UpdateSelfPerceptionResource resource) {
        return mapper.map(resource, SelfPerception.class);
    }

    public Page<SelfPerceptionResource> modelListPage(List<SelfPerception> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, SelfPerceptionResource.class), pageable, modelList.size());
    }
}