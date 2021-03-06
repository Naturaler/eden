package com.yrx.simple.life.eden.web.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class EdenRequestMatcher implements RequestMatcher {
    private String pattern;

    public EdenRequestMatcher(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        log.info("request.getServletPath(): {}", request.getServletPath());
        return request.getServletPath().endsWith(pattern);
    }
}
