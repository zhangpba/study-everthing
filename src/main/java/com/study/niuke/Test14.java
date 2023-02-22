package com.study.niuke;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author zhangpba
 * @description HJ14 字符串排序
 * @date 2023/2/22
 */
public class Test14 {

    public static void main(String[] args) {
        process02();
    }


    private static void process01() {
        Scanner scanner = new Scanner(System.in);
        // 输入数字
        Integer num = scanner.nextInt();

        TreeSet<String> treeSet = new TreeSet();

        for (int i = 0; i <= num; i++) {
            treeSet.add(scanner.nextLine());
        }

        treeSet.remove("");
        Iterator<String> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void process02() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            // 输入数字.如果用注释掉的部分，执行不成功
//            Integer num = scanner.nextInt();
            Integer num = Integer.valueOf(scanner.nextLine());
            String[] strings = new String[num];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = scanner.nextLine();
            }

            Arrays.sort(strings);
            for (int i = 0; i < strings.length; i++) {
                System.out.println(strings[i]);
            }
        }
    }
}
