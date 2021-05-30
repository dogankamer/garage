package com.dogankamer.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
public class OpenAPIConfig {

    @Value("${openapi.server.url:localhost}")
    private String serverUrl;
    @Value("${openapi.server.title:Garage API}")
    private String title;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addServersItem(new Server().url(serverUrl))
                .info(new Info().title(title));
    }
}
