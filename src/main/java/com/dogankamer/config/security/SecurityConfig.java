package com.dogankamer.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {



    @Value("${server.base-path:}")
    private String basePath;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;



    public SecurityConfig(AuthenticationManager authenticationManager, SecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        String[] allowedPatterns = getAllowedPatterns();

        return http.cors().disable()
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> Mono.fromRunnable(() -> swe.getResponse()
                        .setStatusCode(HttpStatus.UNAUTHORIZED)))
                .accessDeniedHandler((swe, e) -> Mono.fromRunnable(() -> swe.getResponse()
                        .setStatusCode(HttpStatus.FORBIDDEN))).and()
                .csrf().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(allowedPatterns).permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(basePath + "/**").authenticated()
                .anyExchange().authenticated()
                .and()
                .build();
    }

    private String[] getAllowedPatterns() {
        return new String[]{
                basePath + "/swagger-resources/**",
                basePath + "/swagger-ui.html",
                basePath + "/swagger-ui.html/**",
                basePath + "/v3/api-docs/swagger-config",
                basePath + "/v3/api-docs",
                basePath + "/v3/api-docs/**",
                basePath + "/configuration/**",
                basePath + "/swagger*/**",
                basePath + "/webjars/**"
//                , basePath + "/**"
        };
    }

}
