package com.vytrack.utils;

import org.codehaus.plexus.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class DataUtil {
    private static final String key = "AD#$ouwed32ljads"; // 128 bit key
    private static final String initVector = "randomstringvect"; // 16 bytes IV

//    public static String encrypt(String value) {
//        try {
//            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
//            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
//
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
//
//            byte[] encrypted = cipher.doFinal(value.getBytes());
//
//            //return Base64.encodeBase64String(encrypted);
//            return "";
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return null;
//    }

//    public static String decrypt(String encrypted) {
//        try {
//            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
//            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
//
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
//            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
//
//            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
//
//            return new String(original);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return null;
//    }
public static String encrypt(String value) {
    Base64 base64 = new Base64();
    return new String(base64.encode(value.getBytes()));
}

    public static String decrypt(String value) {
        Base64 base64 = new Base64();
        return new String(base64.decode(value.getBytes()));
    }
}