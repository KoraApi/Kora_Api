package com.kora.koramonoapi.History.mapping;

import com.kora.koramonoapi.History.domain.model.entity.FavoriteRecord;
import com.kora.koramonoapi.Profile.mapping.PersonalInformationMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("historyFavoritesMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public HistoryRecordMapper historyRecordMapper() { return new HistoryRecordMapper();}

    @Bean
    public FavoriteRecordMapper favoriteRecordMapper() { return new FavoriteRecordMapper();}
}
