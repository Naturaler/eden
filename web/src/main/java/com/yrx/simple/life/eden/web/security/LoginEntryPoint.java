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
        log.info("request.getServletPath(): {}", request.getServletPath());
//        response.sendRedirect("http://localhost:4200/angular/login");
//        response.sendRedirect("http://localhost:18512/eden/custom_login.html");
//        response.sendRedirect("/eden/custom_login.html");
//        response.sendRedirect("http://localhost:18512/eden/custom_login.html");
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        HttpResponse<String> rsp = new HttpResponse<>();
        rsp.setHttpCode(403);
        rsp.setHttpMsg("无访问权限");
        writer.write(new Gson().toJson(rsp));
    }
}
