package com.study.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {
    private static final Base64.Decoder DECODER = Base64.getDecoder();

    private static final Base64.Encoder ENCODER = Base64.getEncoder();

    /**
     * 加密
     * @param text
     * @return
     */
    public static String encrypt(String text) {
        byte[] textArray = null;
        try {
            textArray = text.getBytes("utf-8");
            return ENCODER.encodeToString(textArray);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 解密
     * @param cipher
     * @return
     */
    public static String decrypt(String cipher) {
        byte[] array = DECODER.decode(cipher);
        String text = null;
        try {
            text = new String(array, "utf-8");
            return text;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }



    public static void main(String[] args) {
        String ss = "SeY/cDq/fuiJl7Jko3Ioq9L/5Ouiw9Cj9Bpo/bB23A/8TeA17nDDK7If8whRnnyysTzsgdLTGZ1kQVowSTFz28QO2dibwrmNyUtSgaemmC8g62OYqIuYQG19ASuszuWk";

        String result = decrypt(ss);

        System.out.println(result);
    }
}
