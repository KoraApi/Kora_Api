package com.kora.koramonoapi.Profile.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("personalInformationMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PersonalInformationMapper personalInformationMapper() { return new PersonalInformationMapper();}
}
