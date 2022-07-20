package com.yrx.simple.life.eden.web.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class EdenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    protected EdenAuthenticationProcessingFilter() {
        // 命中时，触发登录校验流程
        super(new EdenRequestMatcher("/login"));
//        setContinueChainBeforeSuccessfulAuthentication(true);
//        setAuthenticationSuccessHandler(new EdenAuthenticationSuccessHandler());
    }
//    EdenAuthenticationProcessingFilter() {
//        super(matcher);
//        AntPathRequestMatcher matcher = new AntPathRequestMatcher("/login");
//    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        log.info("phone: {}, password: {}",phone, password);
//        if (!StringUtils.hasText(phone) || !StringUtils.hasText(password)) {
//            log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
//            throw new BadCredentialsException("鉴权信息不合法");
//        }
        return getAuthenticationManager().authenticate(new EdenAuthenticationToken(phone, password));
    }
}
