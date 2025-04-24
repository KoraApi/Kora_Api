package com.kora.koramonoapi.History.mapping;

import com.kora.koramonoapi.History.domain.model.entity.FavoriteRecord;
import com.kora.koramonoapi.History.resource.CreateFavoriteRecordResource;
import com.kora.koramonoapi.History.resource.FavoriteRecordResource;
import com.kora.koramonoapi.Shared.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class FavoriteRecordMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public FavoriteRecordResource toResource(FavoriteRecord model) {
        return mapper.map(model, FavoriteRecordResource.class);
    }

    public FavoriteRecord toModel(CreateFavoriteRecordResource resource) {
        return mapper.map(resource, FavoriteRecord.class);
    }


    public Page<FavoriteRecordResource> modelListPage(List<FavoriteRecord> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, FavoriteRecordResource.class), pageable, modelList.size());
    }
}
