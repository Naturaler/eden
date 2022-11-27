package com.yrx.simple.life.eden.web.security;

import com.yrx.simple.life.eden.application.dto.ApiResponse;
import com.yrx.simple.life.eden.application.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

@Slf4j
public class EdenAuthenticationProvider implements AuthenticationProvider {
    private AuthenticationService authenticationService;

    public EdenAuthenticationProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof EdenAuthenticationToken) {
            EdenAuthenticationToken edenAuthenticationToken = (EdenAuthenticationToken) authentication;
            log.info("鉴权token: {}", edenAuthenticationToken);
            ApiResponse<String> authResult = authenticationService.authenticate(edenAuthenticationToken.getPrincipal().toString(),
                    edenAuthenticationToken.getCredentials().toString());
            if (authResult.getCode().equals(200)) {
                return new EdenAuthenticationToken(Collections.singletonList(new SimpleGrantedAuthority("root")),
                        edenAuthenticationToken.getPrincipal().toString(), edenAuthenticationToken.getCredentials().toString());
            }
        }
        throw new BadCredentialsException("鉴权信息不合法");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return EdenAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
