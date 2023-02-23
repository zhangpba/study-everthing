package com.study.niuke.test20;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author zhangpba
 * @description https://www.nowcoder.com/practice/3245215fffb84b7b81285493eae92ff0?tpId=37&tqId=21226&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=undefined&judgeStatus=undefined&tags=&title=
 * @date 2023/2/21
 */
public class Test03 {

    /**
     * 描述
     * 明明生成了N个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
     *
     * 数据范围：
     * 1≤n≤1000，
     * 输入的数字大小满足
     * 1≤val≤500
     *
     * 输入描述：
     * 第一行先输入随机整数的个数 N 。 接下来的 N 行每行输入一个整数，代表明明生成的随机数。 具体格式可以参考下面的"示例"。
     * 输出描述：
     * 输出多行，表示输入数据处理后的结果
     *
     * 输入：
     * 3
     * 2
     * 2
     * 1
     * 复制
     * 输出：
     * 1
     * 2
     * 复制
     * 说明：
     * 输入解释：
     * 第一个数字是3，也即这个小样例的N=3，说明用计算机生成了3个1到500之间的随机整数，接下来每行一个随机数字，共3行，也即这3个随机数字为：
     * 2
     * 2
     * 1
     * 所以样例的输出为：
     * 1
     * 2
     *
     * @param args
     */
    public static void main(String[] args) {
//        int num = 3;
//        int[] ints = {2, 2, 1};

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] ints = new int[num];

        for (int i = 0; i < ints.length; i++) {
            ints[i] = scanner.nextInt();
        }
        process(num, ints);

    }

    private static void process(int num, int[] ints) {
        // TreeSet集合底层实际上是一个TreeMap，而TreeMap集合底层是一个二叉树，也将TreeSet集合中的元素称为可排序集合
        TreeSet treeSet = new TreeSet<>();
        for (int i = 0; i < ints.length; i++) {
            treeSet.add(ints[i]);
        }

        Iterator iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
