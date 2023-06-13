package com.unibuc.clientservice.config;

import io.swagger.annotations.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Spring Cloud application API")
                .version("1")
                .description("demo Spring Cloud"));
    }
}
