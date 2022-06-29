package com.study.calcu;

import java.util.Scanner;

/**
 * @author zhangpba
 * @description 题目描述：
 * 如果三个正整数A、B、C ,A²+B²=C²则为勾股数
 * 如果ABC之间两两互质，即A与B，A与C，B与C均互质没有公约数，
 * 则称其为勾股数元组。
 * 请求出给定n~m范围内所有的勾股数元组
 * <p>
 * 输入描述
 * 起始范围
 * 1 < n < 10000
 * n < m < 10000
 * <p>
 * 输出描述
 * ABC保证A<B<C
 * 输出格式A B C
 * 多组勾股数元组，按照A B C升序的排序方式输出。
 * 若给定范围内，找不到勾股数元组时，输出Na。
 * @date 2022/6/27
 */
public class Querstion001 {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            solution(n, m);
        } catch (Exception e) {

        }

    }

    private static void solution(int n, int m) {
        int count = 0;
        for (int a = n; a < m - 1; a++) { // 第一个数
            for (int b = a + 1; b < m; b++) { // 第二个数
                for (int c = b + 1; c < m; c++) { //第三个数
                    // int a = Math.pow(b, 2);//表示b的平方
                    if (relativelyPrime(a, b) && relativelyPrime(b, c) && relativelyPrime(a, c) && (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2))) {
                        count++;
                        System.out.printf("%d %d %d \n", a, b, c);
                    }
                }
            }
        }
        if (count == 0) {
            System.out.println("Na");
        }
    }

    // 是否互质
    private static boolean relativelyPrime(int x, int y) {
        int min = Math.min(x, y);
        // 开根号
        double sqrt = Math.sqrt(min);
        for (int i = 0; i < sqrt; i++) {
            if (x % i == 0 && y % i == 0) {
                return false;
            }
        }
        return true;
    }
}
