package com.yrx.simple.life.eden.application.service;

import com.yrx.simple.life.eden.application.dto.ApiResponse;

public interface AuthenticationService {

    ApiResponse<String> authenticate(String phone, String password);
}
