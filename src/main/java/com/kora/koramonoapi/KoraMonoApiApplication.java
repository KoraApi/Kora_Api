package com.kora.koramonoapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KoraMonoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KoraMonoApiApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Kora API")
                        .version("1.0")
                        .description("Kora API, made with java and springboot, applying domain-driven architecture approach.")
                        .termsOfService(".")
                        .license(new License()
                                .name("Apache 2.0 License")
                                .url(""))
                        .contact(new Contact()
                                .url("")
                                .name("Kora,.studio")));

    }

}
