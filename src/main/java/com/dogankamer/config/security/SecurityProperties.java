package com.dogankamer.config.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("security.authentication")
@Data
public class SecurityProperties {
    private Set<String> tokens;
}
