package com.yrx.simple.life.eden.web.security;

import com.google.gson.Gson;
import com.yrx.simple.life.eden.application.dto.req.AuthenticationReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
public class EdenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    protected EdenAuthenticationProcessingFilter() {
        // 命中时，触发登录校验流程
        super(new EdenRequestMatcher("/login"));
        setAuthenticationSuccessHandler(new EdenSuccessHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String reqBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        log.debug("收到鉴权数据 reqBody: {}", reqBody);
        if (StringUtils.hasText(reqBody)) {
            Gson gson = new Gson();
            AuthenticationReq authenticationReq = gson.fromJson(reqBody, AuthenticationReq.class);
            return getAuthenticationManager().authenticate(new EdenAuthenticationToken(authenticationReq.getAccount(), authenticationReq.getPassword()));
        }
        throw new BadCredentialsException("鉴权数据不合法");
    }
}
