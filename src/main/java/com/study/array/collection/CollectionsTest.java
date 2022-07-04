package com.study.array.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * @author zhangpba
 * @description 对集合排序
 * @date 2022/7/4
 */
public class CollectionsTest {
    public static void main(String[] args) {
        HashSet set = new HashSet<>();
        set.add(1);
        set.add(13);
        set.add(34);
        set.add(24);
        set.add(23);
        set.add(21);
        set.add(30);

        // 给set排序:使用Collections的sort的方法,不能直接给set排序，转化为Array进行排序
        List<Integer> list = new ArrayList<>(set);
        // 从小到大排序
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("========================");


        // 从大到小排序
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
//                return o1 - o2; // 生序
                return o2 - o1; // 降序
            }
        });

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
