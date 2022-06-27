package com.study.jiami;

/**
 * sha256和MD5加密测试类
 *
 * @author zhangpba
 * @date 2022-01-28
 */
public class Sha256AndMd5 {

    public static void main(String[] args) {
        testMd5();
        testSha256();
    }

    public static void testMd5() {
        SecurityUtil securityUtil = new SecurityUtil();
        String str = "hello";
        str = securityUtil.md5Encode(str.getBytes());
        System.out.println(str);
    }

    public static void testSha256() {
        SecurityUtil securityUtil = new SecurityUtil();
        String str = "hello";
        str = securityUtil.sha256Encode(str.getBytes());
        System.out.println(str);
    }
}
