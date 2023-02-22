package com.study.niuke;

import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ6 质数因子
 * @date 2023/2/22
 */
public class Test06 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Integer info = scanner.nextInt();

        process(info);
    }

    private static void process(Integer info) {
        // 因子从2开始，从小到大依次检测
        for (int i = 2; i <= info; i++) {
            // 如果是因子，就输出
            if ((info % i) == 0) {
                System.out.print(i + " ");
                process(info / i);
                break;
            }
            if (i == info) {
                System.out.println(i + "");
            }

        }
    }
}
