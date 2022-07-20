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
        if (request.getServletPath().endsWith(pattern)) {
            return true;
//            try {
//                String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//                if (StringUtils.hasText(body)) {
//                    return true;
//                }
//            } catch (IOException e) {
//                log.error("获取请求数据异常: {}", e.getMessage());
//                e.printStackTrace();
//            }
        }
        return false;
    }
}
