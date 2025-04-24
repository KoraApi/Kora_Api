package com.kora.koramonoapi.IAM.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("identityAccessMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PatientMapper patientMapper() { return new PatientMapper();}
}
