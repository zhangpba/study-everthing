package com.study.algorithm.service;

import com.study.algorithm.IArraySort;

/**
 * 希尔排序
 *
 * @author zhangpba
 * @date 2021-07-08
 */
public class XierSort implements IArraySort {

    @Override
    public String getName() {
        return "希尔排序";
    }

    @Override
    public int[] sort(int[] sourceArray) {

        int length = sourceArray.length;
        int temp;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                temp = sourceArray[i];
                int j = i - step;
                while (j >= 0 && sourceArray[j] > temp) {
                    sourceArray[j + step] = sourceArray[j];
                    j -= step;
                }
                sourceArray[j + step] = temp;
            }
        }

        return sourceArray;
    }
}
