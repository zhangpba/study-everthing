package com.study.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试排查内存溢出的的解决方案
 *
 * @author zhangpba
 * @date 2021-11-01
 */
public class HeapOOM {

    private int heapObjectNum;

    public HeapOOM(int heapObjectNum) {
        this.heapObjectNum = heapObjectNum;
    }

    public static void main(String[] args) throws Exception{
        List<HeapOOM> list = new ArrayList<>();
        // 不断往集合中放入元素，让其内存溢出
        int i = 0;
        while (true){
//            Thread.sleep(1);
//            System.out.println(i++);
            list.add(new HeapOOM(i));
        }
    }
}
