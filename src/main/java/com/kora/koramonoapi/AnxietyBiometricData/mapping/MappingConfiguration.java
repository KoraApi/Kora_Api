package com.kora.koramonoapi.AnxietyBiometricData.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("healthDataMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PhysiologicalDataMapper physiologicalDataMapper() { return new PhysiologicalDataMapper();}

    @Bean
    public AnxietyAssessmentMapper anxietyAssessmentMapper() { return new AnxietyAssessmentMapper();}
}
