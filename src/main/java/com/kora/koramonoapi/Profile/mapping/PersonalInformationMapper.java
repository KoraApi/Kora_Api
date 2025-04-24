package com.kora.koramonoapi.Profile.mapping;

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

public class PersonalInformationMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public PersonalInformationResource toResource(PersonalInformation model) {
        return mapper.map(model, PersonalInformationResource.class);
    }

    public PersonalInformation toModel(CreatePersonalInformationResource resource) {
        return mapper.map(resource, PersonalInformation.class);
    }

    public PersonalInformation toModel(UpdatePersonalInformationResource resource) {
        return mapper.map(resource, PersonalInformation.class);
    }

    public Page<PersonalInformationResource> modelListPage(List<PersonalInformation> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, PersonalInformationResource.class), pageable, modelList.size());
    }
}
