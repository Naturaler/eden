package com.yrx.simple.life.eden.domain.entity;

import com.yrx.simple.life.eden.domain.util.AesUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.security.GeneralSecurityException;

@Data
@Slf4j
public class Antidote {
    private Long id;
    private String title;
    private String key;
    private String val;
    private String remark;

    public void encrypt() {
        final String ENCRYPT_KEY = "2022-07-27000000";
        encrypt(ENCRYPT_KEY);
    }

    public void encrypt(String key) {
        try {
            String encrypt = AesUtil.encrypt(key, val);
            log.debug("解药[{}]加密成功: 加密前: {}, 加密后: {}", this, val, encrypt);

            setVal(encrypt);
        } catch (GeneralSecurityException e) {
            log.error("解药[" + this + "]加密失败，返回明文", e);
        }
    }
}
