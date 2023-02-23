package com.study.niuke.test20;

import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ1 字符串最后一个单词的长度
 * @date 2023/2/21
 */
public class Test01 {

    public static void main(String[] args) {
//        String info = "hello nowcoder";
        Scanner scanner = new Scanner(System.in);
        String info = scanner.nextLine();
        process(info);
    }

    private static void process(String info) {
        if (info == null || info.isEmpty()) {
            return;
        }
        String[] strings = info.split(" ");
        String last = strings[strings.length - 1];
        System.out.println(last.length());
    }
}
