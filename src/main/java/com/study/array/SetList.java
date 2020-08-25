package com.study.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 2020-01-09
 * Set练习
 */
public class SetList {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        Set<String> set1 = new HashSet();
        set1.add("aa");
        set1.add("bb");
        set1.add("cc");
        set1.add("dd");
        set1.add("ee");

        Set<String> set2 = new HashSet();
        set2.add("dd");
        set2.add("ee");
        set2.add("ff");

        // 去除set1和set2中共有的，留下set1中特有的
        set1.removeAll(set2);

        // 交集
//        set1.retainAll(set2);

        for (String str : set1) {
            System.out.println(str);
        }
    }
}
