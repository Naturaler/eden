package com.yrx.simple.life.eden.web.security;

import com.google.gson.Gson;
import com.yrx.simple.life.eden.application.dto.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class LoginEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("响应登录认证节点 request.getServletPath(): {}", request.getServletPath());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        HttpResponse<String> rsp = new HttpResponse<>();
        rsp.setHttpCode(403);
        rsp.setHttpMsg("无访问权限");
        writer.write(new Gson().toJson(rsp));
    }
}
