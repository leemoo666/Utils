package com.ycx.former.utils;

import android.util.Base64;
import android.util.Log;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSAUtilsBak {

    public static final String PRIVATE_KEY =
            "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAOlEp3qS8mZH8mZX" +
                    "cdU2D+OpgG1cxbql7fPC434U2rJrIZuMNsonB5rvvZnCe59TutBncZl3OpHSbJYi" +
                    "V9m3/HCClVgcTkZnkO9p1WHqGYUZJxVHLLo5qNvNuxK/P4RDrzrC+R18GRqGcwcC" +
                    "Xmne37hhHx5u9/wnSQfhbWDl18khAgMBAAECgYAWH/Tio11qZhiPrGuXlXEcXs9X" +
                    "kB0Q/vv6ytlY9BFqKs8dOcdYlSsbfvG0y8V97ysg6S7VJ4Heot65vrgmZlUDCIBT" +
                    "D8VahTc5dGWH0CXyF2jfcJQ9PMWFF3oHooKbp6OIHMkcX6lDqX6g7tn2HTrRV+qg" +
                    "LuRi6CtCLsRO2OgAAQJBAPrsZmyHr7ENJzZCa4Zb1PhFffMi6vJT2V2lZpwhUkK0" +
                    "pi0JIg8gBbbcLLZPmilOHCg3SPqlHE6/FWWds3UuoQECQQDt/NATlriK89t9v0SR" +
                    "9ZsmsIwRAQD8wfz14vhjRc69eHL3aS4h81fo3UKDnpYVvIAawplti8yMgZ0RH9Zo" +
                    "zQghAkAYDVT8Dj2PER2zG8bGyEKv0alzBuPSeqsZSON/D8qczkgnhPqYzI7/qjc2" +
                    "+oZiczRLvpDmRbFi8voQFX/AOcoBAkBAjQIjTg5gIiS2xtpifKvIorpbIlqSNEvY" +
                    "peeZhaW93p3QXccVjluZ3encqXcsDiTxHTdgbzrcIYvoySnu4y4BAkBFEPYmjTW5" +
                    "FfoRHwaga7O7Ug+MZtoSu2lWmjN9M1Z8NJdod4A71uR35JDPRkbsZmSiwmBd6yGn" +
                    "tG43Y/WP9H+J";
    private static final String PUBLIC_KEY =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDpRKd6kvJmR/JmV3HVNg/jqYBt" +
                    "XMW6pe3zwuN+FNqyayGbjDbKJwea772ZwnufU7rQZ3GZdzqR0myWIlfZt/xwgpVY" +
                    "HE5GZ5DvadVh6hmFGScVRyy6OajbzbsSvz+EQ686wvkdfBkahnMHAl5p3t+4YR8e" +
                    "bvf8J0kH4W1g5dfJIQIDAQAB";
    /**
     * 加密算法RSA
     */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 公钥加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(PUBLIC_KEY, 0));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey1 = keyFactory.generatePublic(keySpec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey1);
        byte[] cipherData = cipher.doFinal(data.getBytes());
        Log.i("lxm", "cipherText：" + Base64.encodeToString(cipherData, 0));
        return Base64.encodeToString(cipherData, 0);
    }

    /**
     * 私钥解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PKCS8EncodedKeySpec keySpec1 = new PKCS8EncodedKeySpec(Base64.decode(PRIVATE_KEY, 0));
        PrivateKey privateKey1 = keyFactory.generatePrivate(keySpec1);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey1);
        byte[] plainData = cipher.doFinal(Base64.decode(data, 0));
        return new String(plainData);
    }


    public static void test(String plainText) throws Exception {

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(PUBLIC_KEY, 0));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey1 = keyFactory.generatePublic(keySpec);

        PKCS8EncodedKeySpec keySpec1 = new PKCS8EncodedKeySpec(Base64.decode(PRIVATE_KEY, 0));
        PrivateKey privateKey1 = keyFactory.generatePrivate(keySpec1);


        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey1);
        byte[] cipherData = cipher.doFinal(plainText.getBytes());
        Log.i("lxm", "" + cipherData);
        Log.i("lxm", "cipherText：" + Base64.encodeToString(cipherData, 0));

        cipher.init(Cipher.DECRYPT_MODE, privateKey1);
        String content = Base64.encodeToString(cipherData, 0);
        byte[] plainData = cipher.doFinal(Base64.decode(content, 0));
        Log.i("lxm", "plainText：" + new String(plainData));
    }

}