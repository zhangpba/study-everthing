package com.study.niuke;

import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ13 句子逆序
 * @date 2023/2/22
 */
public class Test13 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入数字串
        String info = scanner.nextLine();

        process(info);
    }

    private static void process(String info) {

        String[] split = info.split(" ");

        StringBuffer buffer = new StringBuffer();
        for (int i = split.length - 1; i >= 0; i--) {
            buffer.append(split[i]);
            buffer.append(" ");
        }

        System.out.printf(buffer.toString().trim());
    }
}
