package com.study.niuke.test20;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author zhangpba
 * @description HJ8 合并表记录
 *
 * 如果用HashMap的话 不能进行排序，所以使用Treemap或者转化为TreeSet，再进行遍历
 * @date 2023/2/22
 */
public class Test08 {

    public static void main(String[] args) {
        process02();
    }

    private static void process01() {
        Scanner scanner = new Scanner(System.in);
        // 数组的大小
        int size = scanner.nextInt();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < size; i++) {
            int key = scanner.nextInt();
            int value = scanner.nextInt();

            if (map.get(key) == null) {
                map.put(key, value);
            } else {
                Integer val = map.get(key);
                map.put(key, val + value);
            }
        }

        TreeSet<Integer> treeSet = new TreeSet<Integer>(map.keySet());

        Iterator<Integer> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.println(key + " " + map.get(key));
        }

        // 如果用HashMap的话 不能进行排序，所以使用Treemap或者转化为TreeSet，再进行遍历
//        for (Integer key : treeSet) {
//            System.out.println(key + " " + map.get(key));
//        }
    }

    private static void process02() {
        Scanner scanner = new Scanner(System.in);
        // 数组的大小
        int size = scanner.nextInt();
        Map<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < size; i++) {
            int key = scanner.nextInt();
            int value = scanner.nextInt();

            if (map.get(key) == null) {
                map.put(key, value);
            } else {
                Integer val = map.get(key);
                map.put(key, val + value);
            }
        }

        // 如果用HashMap的话 不能进行排序，所以使用TreeSet
        for (Integer key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
    }
}
