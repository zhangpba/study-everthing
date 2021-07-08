package com.study.algorithm;

import com.study.algorithm.service.*;

import java.util.Random;

/**
 * 排序算法测试类
 *
 * @author zhangpba
 * @date 2021-07-08
 */
public class SortMain {

    public static void main(String[] args) {
        // 需要排序的数组
//        int[] sourceArray = {3, 5, 7, 5, 12, 5, 4, 65, 23, 45, 2};

        // 随机产生一个数组
        int[] sourceArray = getArray(100000);

        IArraySort baseSort = new BaseSort();
        IArraySort duiSort = new DuiSort();
        IArraySort guibinSort = new GuibinSort();
        IArraySort insertSort = new InsertSort();
        IArraySort jishuSort = new JishuSort();
        IArraySort kuaisuSort = new KuaisuSort();
        IArraySort maopaoSort = new MaopaoSort();
        IArraySort tongSort = new TongSort();
        IArraySort xierSort = new XierSort();
        IArraySort xuezeSort = new XuezeSort();

        // 计算排序时间
        dateSclac(baseSort, sourceArray);
        dateSclac(duiSort, sourceArray);
        dateSclac(guibinSort, sourceArray);
        dateSclac(insertSort, sourceArray);
        dateSclac(jishuSort, sourceArray);
        dateSclac(kuaisuSort, sourceArray);
        dateSclac(maopaoSort, sourceArray);
        dateSclac(tongSort, sourceArray);
        dateSclac(xierSort, sourceArray);
        dateSclac(xuezeSort, sourceArray);

//        foreach(dateSclac(xuezeSort, sourceArray));
    }

    /**
     * 计算排序需要的时间
     *
     * @param arraySort   排序方式
     * @param sourceArray 需要排序的数组
     * @return 排序后的数组
     */
    private static int[] dateSclac(IArraySort arraySort, int[] sourceArray) {
        long startDate = System.currentTimeMillis();
        int[] targetArray = arraySort.sort(sourceArray);
        long endDate = System.currentTimeMillis();
        System.out.println(arraySort.getName() + "====开始时间：" + startDate + "结束时间：" + endDate + "用时：" + (endDate - startDate));
        return targetArray;
    }


    /**
     * 遍历排序后的数组
     *
     * @param targetArray
     */
    private static void foreach(int[] targetArray) {
        for (int num : targetArray) {
            System.out.println(num);
        }
    }


    /**
     * 随机产生一个数组
     *
     * @param arrSize 指定数组的大小
     * @return 产生的随机数组
     */
    private static int[] getArray(int arrSize) {
        Random random = new Random();
        int[] arr = new int[arrSize];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10000);
        }

        for (int i = 0; i < arr.length - 1; i++) {
            System.out.println(arr[i] + " ");
        }

        return arr;

    }
}
