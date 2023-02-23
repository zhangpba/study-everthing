package com.study.niuke.test20;

import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ9 提取不重复的整数
 * @date 2023/2/22
 */
public class Test09 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入数字串
        String info = scanner.nextLine();

        // 先将字符串反转一下
        StringBuffer bufferInfo = new StringBuffer(info);
        info = bufferInfo.reverse().toString();

        StringBuffer buffer = new StringBuffer();

        // 遍历整个字符串
        for (int i = 0; i < info.length(); i++) {
            // 获取第n个字符
            String a = info.substring(i, i + 1);
            // 结果中是否已经包含字符
            if (!buffer.toString().contains(a)) {
                buffer.append(a);
            }
        }

        System.out.println(buffer.toString());
    }
}
