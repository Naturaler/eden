package com.yrx.simple.life.eden.domain.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

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
            byte[] encrypt = encrypt(key.getBytes(StandardCharsets.UTF_8), val.getBytes(StandardCharsets.UTF_8));
            String encryptVal = Base64.getEncoder().encodeToString(encrypt);
            log.debug("解药[{}]加密成功: 加密前: {}, 加密后: {}", this, val, encryptVal);

            setVal(encryptVal);
        } catch (GeneralSecurityException e) {
            log.error("解药[" + this + "]加密失败，返回明文", e);
        }
    }

//    public static void main(String[] args) throws Exception {
//        // 原文:
//        String message = "my message";
//        System.out.println("Message: " + message);
//        // 128位密钥 = 16 bytes Key:
//        byte[] key = "1234567890abcdef".getBytes(StandardCharsets.UTF_8);
//        // 加密:
//        byte[] data = message.getBytes(StandardCharsets.UTF_8);
//        byte[] encrypted = encrypt(key, data);
//        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
//        // 解密:
//        byte[] decrypted = decrypt(key, encrypted);
//        System.out.println("Decrypted: " + new String(decrypted, StandardCharsets.UTF_8));
//    }

    // 加密:
    private byte[] encrypt(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }

    // 解密:
    private byte[] decrypt(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }
}
