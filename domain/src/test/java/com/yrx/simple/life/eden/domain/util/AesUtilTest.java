package com.yrx.simple.life.eden.domain.util;

import org.junit.jupiter.api.Test;

import java.security.GeneralSecurityException;

class AesUtilTest {
    @Test
    public void test() throws GeneralSecurityException {
        String key = "1234567890abcdef";
        String encrypt = AesUtil.encrypt(key, "hello world");
        System.out.println("encrypt = " + encrypt);

        String decrypt = AesUtil.decrypt(key, encrypt);
        System.out.println("decrypt = " + decrypt);
    }

}