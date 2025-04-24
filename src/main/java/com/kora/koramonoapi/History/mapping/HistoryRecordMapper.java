package com.kora.koramonoapi.History.mapping;

import com.kora.koramonoapi.History.domain.model.entity.HistoryRecord;
import com.kora.koramonoapi.History.resource.CreateHistoryRecordResource;
import com.kora.koramonoapi.History.resource.HistoryRecordResource;
import com.kora.koramonoapi.Profile.domain.model.entity.PersonalInformation;
import com.kora.koramonoapi.Profile.resource.CreatePersonalInformationResource;
import com.kora.koramonoapi.Profile.resource.PersonalInformationResource;
import com.kora.koramonoapi.Profile.resource.UpdatePersonalInformationResource;
import com.kora.koramonoapi.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class HistoryRecordMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public HistoryRecordResource toResource(HistoryRecord model) {
        return mapper.map(model, HistoryRecordResource.class);
    }

    public HistoryRecord toModel(CreateHistoryRecordResource resource) {
        return mapper.map(resource, HistoryRecord.class);
    }


    public Page<HistoryRecordResource> modelListPage(List<HistoryRecord> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, HistoryRecordResource.class), pageable, modelList.size());
    }
}
