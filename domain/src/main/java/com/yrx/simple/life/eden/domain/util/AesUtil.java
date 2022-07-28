package com.yrx.simple.life.eden.domain.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

@Slf4j
public class AesUtil {

    /**
     * 加密
     */
    public static String encrypt(String key, String input) throws GeneralSecurityException {
        if (!StringUtils.hasText(key) || !StringUtils.hasText(input)) {
            throw new IllegalArgumentException("加密入参不合法！不允许为空！");
        }
        String encrypt = Base64.getEncoder().encodeToString(encrypt(key.getBytes(StandardCharsets.UTF_8), input.getBytes(StandardCharsets.UTF_8)));
        log.debug("加密前: key: {}, input: {}; 加密后: val: {}", key, input, encrypt);
        return encrypt;
    }

    /**
     * 加密
     */
    public static byte[] encrypt(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(input);
    }

    /**
     * 解密
     */
    public static String decrypt(String key, String input) throws GeneralSecurityException {
        if (!StringUtils.hasText(key) || !StringUtils.hasText(input)) {
            throw new IllegalArgumentException("加密入参不合法！不允许为空！");
        }
        String decrypt = new String(decrypt(key.getBytes(StandardCharsets.UTF_8), Base64.getDecoder().decode(input.getBytes(StandardCharsets.UTF_8))));
        log.debug("解密前: key: {}, input: {}; 解密后: val: {}", key, input, decrypt);
        return decrypt;
    }

    /**
     * 解密
     */
    public static byte[] decrypt(byte[] key, byte[] input) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(input);
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
}
