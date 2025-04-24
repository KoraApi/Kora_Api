package com.kora.koramonoapi.AnxietyBiometricData.mapping;

import com.kora.koramonoapi.AnxietyBiometricData.domain.model.entity.PhysiologicalData;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.CreatePhysiologicalDataResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.PhysiologicalDataResource;
import com.kora.koramonoapi.AnxietyBiometricData.resource.PhysiologicalData.UpdatePhysiologicalDataResource;
import com.kora.koramonoapi.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PhysiologicalDataMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public PhysiologicalDataResource toResource(PhysiologicalData model) {
        return mapper.map(model, PhysiologicalDataResource.class);
    }

    public PhysiologicalData toModel(CreatePhysiologicalDataResource resource) {
        return mapper.map(resource, PhysiologicalData.class);
    }

    public PhysiologicalData toModel(UpdatePhysiologicalDataResource resource) {
        return mapper.map(resource, PhysiologicalData.class);
    }

    public Page<PhysiologicalDataResource> modelListPage(List<PhysiologicalData> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, PhysiologicalDataResource.class), pageable, modelList.size());
    }
}
