package com.study.niuke.test40;

import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ21 简单密码
 * @date 2023/2/23
 */
public class Test21 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String info = scanner.nextLine();
        process(info);
    }

    private static void process(String info) {
        String daxie = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String xiaoxie = "abcdefghijklmnopqrstuvwxyz";
        // 大些转化
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < info.length(); i++) {
            // 小些转化
            if (info.charAt(i) >= 'a' && info.charAt(i) <= 'z') {
                if ("abc".contains(info.charAt(i) + "")) {
                    buffer.append("2");
                }
                if ("def".contains(info.charAt(i) + "")) {
                    buffer.append("3");
                }
                if ("ghi".contains(info.charAt(i) + "")) {
                    buffer.append("4");
                }
                if ("jkl".contains(info.charAt(i) + "")) {
                    buffer.append("5");
                }
                if ("mno".contains(info.charAt(i) + "")) {
                    buffer.append("6");
                }
                if ("pqrs".contains(info.charAt(i) + "")) {
                    buffer.append("7");
                }
                if ("tuv".contains(info.charAt(i) + "")) {
                    buffer.append("8");
                }
                if ("wxyz".contains(info.charAt(i) + "")) {
                    buffer.append("9");
                }
            } else if (info.charAt(i) >= 'A' && info.charAt(i) <= 'Z') {
                int index = daxie.indexOf(info.charAt(i) + "");
                // 字符串的长度是0-25
                if (index == 25) {
                    buffer.append("a");
                } else {
                    buffer.append(xiaoxie.substring(index + 1, index + 2));
                }
            } else {
                // 数字和其他字符转化
                buffer.append(info.charAt(i));
            }
        }

        System.out.println(buffer.toString());
    }
}
