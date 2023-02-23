package com.study.niuke.test40;

import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ22 汽水瓶
 * @date 2023/2/23
 */
public class Test22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            Integer bottle = scanner.nextInt();
            process(bottle);
        }
    }

    /**
     * 想要换最多的汽水，就要厚脸皮，每两个空瓶向老板借一瓶汽水，喝完之后拿三个空瓶再换一瓶还给老板；相当于自己每两个空瓶可以换到一瓶汽水
     * 核心代码 bottle/2
     *
     * @param bottle
     */
    private static void process(Integer bottle) {
        if (bottle != 0) {
            System.out.println(bottle / 2);
        }
    }
}
