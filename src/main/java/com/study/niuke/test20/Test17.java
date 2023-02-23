package com.study.niuke.test20;

import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ17 坐标移动
 * @date 2023/2/23
 */
public class Test17 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入数字串
        String info = scanner.nextLine();

        process(info);
    }

    private static void process(String info) {
        String[] strings = info.split(";");
        // 定义坐标
        int x = 0;
        int y = 0;
        for (int i = 0; i < strings.length; i++) {
            String index = strings[i];

            if (index == null || index.length() <= 1) {
                continue;
            }
            // 截取第一个字符
            String pre = index.substring(0, 1);
            if (!("A".equals(pre) || "D".equals(pre) || "W".equals(pre) || "S".equals(pre))) {
                continue;
            }

            int mv = 0;
            try {
                // 截取第二个字符后面的所有字符
                mv = Integer.valueOf(index.substring(1));
            } catch (Exception e) {
                continue;
            }

            if ("A".equals(pre)) {
                x = x - mv;
            }
            if ("D".equals(pre)) {
                x = x + mv;
            }
            if ("W".equals(pre)) {
                y = y + mv;
            }
            if ("S".equals(pre)) {
                y = y - mv;
            }
        }

        System.out.println(x + "," + y);
    }
}
