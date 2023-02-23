package com.study.niuke.test20;

import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ11 数字颠倒
 * @date 2023/2/22
 */
public class Test11 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入数字串
        String info = scanner.nextLine();

        process(info);
    }

    private static void process(String info) {
        StringBuffer buffer = new StringBuffer(info);
        info = buffer.reverse().toString();
        System.out.println(info);
    }
}
