package com.yrx.simple.life.eden.application.dto.req;

import lombok.Data;

@Data
public class AuthenticationReq {
    private String phone;
    private String password;
}
