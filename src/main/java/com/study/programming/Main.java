package com.study.programming;

import java.util.Arrays;

/**
 * 编程练习
 *
 * @author zhangpba
 * @date 2021-07-22
 */
public class Main {

    public static void main(String[] args) {

        int[] array = {3, 2, 4};
        int[] result = twoSum(array, 6);

        System.out.println(Arrays.toString(result));
    }

    /**
     * 给出一个整数数组，请在数组中找出两个加起来等于目标值的数，
     * 你给出的函数twoSum 需要返回这两个数字的下标（index1，index2），需要满足 index1 小于index2.。注意：下标是从1开始的
     * 假设给出的数组中只存在唯一解
     * 例如：
     * 给出的数组为 {20, 70, 110, 150},目标值为90
     * 输出 index1=1, index2=2
     *
     * @param numbers int整型一维数组
     * @param target  int整型
     * @return int整型一维数组
     */
    public static int[] twoSum(int[] numbers, int target) {
        int result[] = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (i == j) {
                    continue;
                }
                int a = numbers[i];
                int b = numbers[j];
                if (a + b == target) {
                    if (i > j) {
                        result[0] = j + 1;
                        result[1] = i + 1;
                    } else {
                        result[0] = i + 1;
                        result[1] = j + 1;
                    }
                }
            }
        }
        return result;
    }

}
