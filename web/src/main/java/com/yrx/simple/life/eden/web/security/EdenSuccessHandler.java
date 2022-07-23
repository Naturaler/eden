package com.yrx.simple.life.eden.web.security;

import com.google.gson.Gson;
import com.yrx.simple.life.eden.application.dto.HttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EdenSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        HttpResponse<String> rsp = new HttpResponse<>();
        rsp.setHttpCode(200);
        rsp.setHttpMsg("登录成功");
        writer.write(new Gson().toJson(rsp));
    }
}
