package com.study.niuke;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ5 进制转换
 * @date 2023/2/21
 */
public class Test05 {
    public static void main(String[] args) {

        System.out.println(Integer.parseInt("100", 16));
        Scanner scanner = new Scanner(System.in);
        process02(scanner);

    }

    private static void process01(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String info = scanner.nextLine();
            System.out.println(Integer.parseInt(info.substring(2, info.length()), 16));
        }
    }

    private static HashMap<Character, Integer> map = new HashMap<Character, Integer>();

    private static void init() {
        map.put('0', 0);
        map.put('1', 1);
        map.put('2', 2);
        map.put('3', 3);
        map.put('4', 4);
        map.put('5', 5);
        map.put('6', 6);
        map.put('7', 7);
        map.put('8', 8);
        map.put('9', 9);
        map.put('A', 10);
        map.put('B', 11);
        map.put('C', 12);
        map.put('D', 13);
        map.put('E', 14);
        map.put('F', 15);
        map.put('a', 10);
        map.put('b', 11);
        map.put('c', 12);
        map.put('d', 13);
        map.put('e', 14);
        map.put('f', 15);
    }

    private final static int BASE = 16;
    private static int process02(Scanner scanner) {
        init();
        String number = scanner.nextLine();
        int res = 0;
        for (char ch : number.toCharArray()) {
            res = res * BASE + map.get(ch);
        }

        System.out.println(res);
        return res;
    }
}
