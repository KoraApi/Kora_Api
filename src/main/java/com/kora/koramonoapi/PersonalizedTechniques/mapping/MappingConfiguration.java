package com.kora.koramonoapi.PersonalizedTechniques.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("personalizedTechniquesMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public TechniqueMapper techniqueMapper() { return new TechniqueMapper();}

    @Bean
    public TechniqueDetailMapper techniqueDetailMapper() { return new TechniqueDetailMapper();}
}
