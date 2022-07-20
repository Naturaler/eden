package com.yrx.simple.life.eden.application.service.impl;

import com.yrx.simple.life.eden.application.dto.ApiResponse;
import com.yrx.simple.life.eden.application.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public ApiResponse<String> authenticate(Object phone, Object password) {
        log.info("鉴权信息-obj：phone: {}, password: {}", phone, password);
        if (phone == null || password == null) {
            return ApiResponse.fail();
        }
        return authenticate(phone.toString(), password.toString());
    }

    @Override
    public ApiResponse<String> authenticate(String phone, String password) {
        log.info("鉴权信息-string：phone: {}, password: {}", phone, password);
        if (!StringUtils.hasText(phone) || !StringUtils.hasText(password)) {
            return ApiResponse.fail();
        }

        if (phone.equals("root") && password.equals("pass")) {
            return ApiResponse.success();
        }
        return ApiResponse.fail(403, "权限校验不通过");
    }
}
