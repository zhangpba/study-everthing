package com.study.niuke.test20;

import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ15 求int型正整数在内存中存储时1的个数
 *
 * Integer中有一些将10进制转化为 2（toBinaryString）、8（toOctalString）、16（toHexString）进制的方法
 * @date 2023/2/22
 */
public class Test15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer num = scanner.nextInt();
        // 先将num转化为二进制
        String str = Integer.toBinaryString(num);
        System.out.println("二进制字符"+str);
        // 然后将字符串中的1全部替换掉
        String repace = str.replaceAll("1", "");
        // 2进制的字符长度-替换后的字符长度
        System.out.println(str.length() - repace.length());
    }

}
