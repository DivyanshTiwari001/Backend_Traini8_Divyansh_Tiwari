package com.github.divyanshtiwari001.traini8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Traini8 API")
                        .version("1.0")
                        .description("Traini8 API Documentation")
                        .contact(new Contact()
                        .name("Traini8 Api Support")
                        .email("traini8@dummy.com"))
                );
        }
}
