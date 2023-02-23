package com.study.niuke.test20;

import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ7 取近似值
 * @date 2023/2/21
 *
 *
 * 输入：5.5
 * 输出：6
 * 输入：2.499
 * 输出：2
 */
public class Test07 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextDouble()) {
            double info = scanner.nextDouble();
            process(info);
        }
    }

    private static void process(double info) {
        // double被强转成int的时候，会自动去掉小数点后边的 0.0000001~0.9999999
        // 需要四射五入，所以给其增加0.5
        Integer result = (int) (info + 0.5);
        System.out.println(result);
    }
}
