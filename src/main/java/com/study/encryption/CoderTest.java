package com.study.encryption;

import org.junit.Test;

import java.math.BigInteger;

public class CoderTest {
    @Test
    public void test() throws Exception {
        String inputStr = "123456abc";
        System.out.println("原文:\n" + inputStr);

        byte[] inputData = inputStr.getBytes();
        String code = Coder.encryptBASE64(inputData);

        System.err.println("BASE64加密后:\n" + code);

        byte[] output = Coder.decryptBASE64(code);

        String outputStr = new String(output);

        System.err.println("BASE64解密后:\n" + outputStr);

        // 验证MD5对于同一内容加密是否一致
        out(Coder.encryptMD5(inputData));
        out(Coder.encryptMD5(inputData));

        // 验证SHA对于同一内容加密是否一致
        out(Coder.encryptSHA(inputData));
        out(Coder.encryptSHA(inputData));

        String key = Coder.initMacKey();
        System.err.println("Mac密钥:\n" + key);

        // 验证HMAC对于同一内容，同一密钥加密是否一致
        out(Coder.encryptHMAC(inputData, key));
        out(Coder.encryptHMAC(inputData, key));

        BigInteger md5 = new BigInteger(Coder.encryptMD5(inputData));
        System.err.println("MD5:\n" + md5.toString(16));

        BigInteger sha = new BigInteger(Coder.encryptSHA(inputData));
        System.err.println("SHA:\n" + sha.toString(32));

        BigInteger mac = new BigInteger(Coder.encryptHMAC(inputData, inputStr));
        System.err.println("HMAC:\n" + mac.toString(16));
    }

    public static void out(byte[] data) throws Exception {
        String a = new String(data, "utf-8");
        System.err.println(a);
    }

}
