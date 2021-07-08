package com.study.algorithm;

/**
 * 排序接口
 *
 * @author zhangpba
 * @date 2021-07-08
 */
public interface IArraySort {

    /**
     * 排序方法
     *
     * @param sourceArray 原始数组
     * @return 排序后的数组
     */
    int[] sort(int[] sourceArray);

    /**
     * 获取排序的名称
     *
     * @return 排序的名称(十种排序方法的名称)
     */
    String getName();
}
