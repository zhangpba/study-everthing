package com.study.calc;

import java.util.Scanner;

/**
 * 题目描述：
 * 一天一只顽猴想要从山脚爬到山顶
 * 途中经过一个有n个台阶的阶梯，但是这个猴子有个习惯，每一次只跳1步或3步
 * 试问？猴子通过这个阶梯有多少种不同的跳跃方式
 * <p>
 * 输入描述
 * 输入只有一个这个数n 0 < n < 50
 * 此阶梯有多个台阶
 * <p>
 * 输出描述
 * 有多少种跳跃方式
 *
 * @author zhangpba
 * @date 2022/7/6
 */
public class Question004 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        sulotion(n);
    }

    // 思路：F(n)=f(n-1)+f(n-3)
    private static void sulotion(int n) {

        int step1 = 1;
        int step2 = 1;
        int step3 = 2;
        int step4 = n == 1 || n == 2 ? 1 : 2;

        // step4 用来存储最后的跳跃方式数量
        // 当n为1或者2的时候只有1种跳跃方式，n大于1或者2时，有两种及以上的跳跃方式
        // n=3的时候，必定有2种跳跃方式
        // n>3的时候，有大于2种跳跃方式

        /**
         * n=1 s1 = 1
         * n=2 s2 = 1
         * n=3 s3 = 2
         * n=4 s4 = 1+2
         * n=5 s5 = 1+3
         * n=6 s6 = 2+4
         * n=7 s7 = 3+6
         */
        for (int i = 4; i <= n; i++) {
            // 观察每一台阶数下的跳跃方式总数
            step4 = step3 + step1;
            step1 = step2;
            step2 = step3;
            step3 = step4;
        }
        System.out.println(step4);
    }

    // 使用递归
    private static int fun(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        if (n == 3) {
            return 3;
        }
        int sum = 0;
        if (n > 3) {
            // F(n) = f(n-1) + f(n-3)
            sum = fun(n - 1) + fun(n - 3);
        }
        return sum;
    }
}
