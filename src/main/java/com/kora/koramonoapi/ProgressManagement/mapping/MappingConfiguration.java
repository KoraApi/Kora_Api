package com.kora.koramonoapi.ProgressManagement.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("progressManagementMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public EmotionalDataMapper emotionalDataMapper() { return new EmotionalDataMapper();}
}
