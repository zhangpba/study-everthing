package com.study.calcu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 题目描述：
 * 给定两个整数数组，arr1、arr2，数组元素按升序排列；
 * 假设从arr1、arr2中分别取出一个元素，可构成一对元素；
 * 现在需要取出k对元素，并对取出的所有元素求和，计算和的最小值；
 * 注意：两对元素对应arr1、arr2的下标是相同的，视为同一对元素。
 * <p>
 * 输入描述
 * 输入两行数组arr1、arr2
 * 每行首个数字为数组大小size， 0 < size <= 100
 * arr1，arr2中的每个元素e， 0< e <1000
 * 接下来一行，正整数k 0 < k <= arr1.size * arr2.size
 * <p>
 * 输出描述
 * 满足要求的最小值
 *
 * @author zhangpba
 * @date 2022/7/4
 */
public class Question002 {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            // 输入第一行
            int[] arr1 = parseArray(scanner.nextLine());
            // 输入第二行
            int[] arr2 = parseArray(scanner.nextLine());
            // 输入第三行
            int k = scanner.nextInt();
            soulution(arr1, arr2, k);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void soulution(int[] arr1, int[] arr2, int k) {
        // 这行代码可以不用写，不用指定集合的长度，k是第三个参数
        int initalCapacity = arr1.length * arr2.length;

        // 两个数组分别取出元素并且求和放到一个排序的集合中
        List<Integer> list = new ArrayList<>(initalCapacity);
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                list.add(arr1[1] + arr2[j]);
            }
        }

        // 元素排序
        Collections.sort(list);

        // 输出结果
        int res = 0;
        for (int i = 0; i < k; i++) {
            System.out.println(list.get(1));
            res += list.get(i);
        }
        System.out.println(res);
    }

    /**
     * 将字符串转化为int数组
     *
     * @param line 一行字符串
     * @return int数组
     */
    private static int[] parseArray(String line) {
        String[] split = line.split(" ");
        int[] arr = new int[split.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        return arr;
    }
}
