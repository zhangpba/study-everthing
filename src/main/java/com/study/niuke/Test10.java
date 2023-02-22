package com.study.niuke;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author zhangpba
 * @description HJ10 字符个数统计
 * @date 2023/2/22
 */
public class Test10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入数字串
        String info = scanner.nextLine();

        process(info);
    }

    private static void process(String info) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < info.length(); i++) {
            set.add(info.charAt(i) + "");
        }
        System.out.println(set.size());
    }
}
