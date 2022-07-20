package com.yrx.simple.life.eden.web.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class EdenAuthenticationToken extends AbstractAuthenticationToken {
    private String phone;
    private String password;

    public EdenAuthenticationToken(String phone, String password) {
        super(null);
        this.phone = phone;
        this.password = password;
        this.setAuthenticated(false);
    }

    public EdenAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String phone, String password) {
        super(authorities);
        this.phone = phone;
        this.password = password;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getPrincipal() {
        return this.phone;
    }
}
