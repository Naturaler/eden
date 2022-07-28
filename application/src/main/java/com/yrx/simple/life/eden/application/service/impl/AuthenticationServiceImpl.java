package com.yrx.simple.life.eden.application.service.impl;

import com.yrx.simple.life.eden.application.dto.ApiResponse;
import com.yrx.simple.life.eden.application.service.AuthenticationService;
import com.yrx.simple.life.eden.domain.util.AesUtil;
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
        String decrypt;
        try {
            decrypt = AesUtil.decrypt("LoginAntidote188", password);
        } catch (Exception e) {
            log.error("登录校验[" + phone + " , " + password + "]密码解密异常！", e);
            return ApiResponse.fail(403, "权限校验不通过");
        }

        String[] items = decrypt.split("-");
        if (items.length != 3) {
            return ApiResponse.fail(403, "权限校验不通过");
        }
        if (!items[0].equals("Antidote")) {
            return ApiResponse.fail(403, "权限校验不通过");
        }
        long loginTimestamp = Long.parseLong(items[2]);
        if (System.currentTimeMillis() - loginTimestamp > (2 * 60 * 1000L)) {
            return ApiResponse.fail(403, "权限校验不通过");
        }
        if (phone.equals("root") && items[1].equals("pass")) {
            return ApiResponse.success();
        }
        return ApiResponse.fail(403, "权限校验不通过");
    }
}
