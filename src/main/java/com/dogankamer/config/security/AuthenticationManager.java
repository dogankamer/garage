package com.dogankamer.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
@Slf4j
public class AuthenticationManager implements ReactiveAuthenticationManager {


    private final SecurityProperties securityProperties;

    public AuthenticationManager(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        if (authToken.startsWith("Bearer ")) {
            authToken = authToken.replace("Bearer ", "");
        }
        if (securityProperties.getTokens().contains(authToken)) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), Collections.emptyList());
            return Mono.just(authenticationToken);
        }
        return Mono.empty();
    }
}
