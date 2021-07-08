package com.study.algorithm.service;

import com.study.algorithm.IArraySort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author zhangpba
 * @date 2021-07-08
 */
public class MaopaoSort implements IArraySort {

    @Override
    public String getName() {
        return "冒泡排序";
    }

    @Override
    public int[] sort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        for (int i = 1; i < arr.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;

            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;

                    flag = false;
                }
            }

            if (flag) {
                break;
            }
        }
        return arr;
    }
}
