package com.study.charType;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String num = scanner.nextLine();

        char[] chars = num.toCharArray();
        String result = "";
        List<String> list = new ArrayList<>();
        for (int i = chars.length - 1; i >= 0; i--) {
            String ch = chars[i] + "";
            // 如果里里面有
            if (!list.contains(ch)) {
                list.add(ch);
            }
        }
        for (String s : list) {
            result += s;
        }

        System.out.println(result);
    }


    public static void four() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int part = str.length() / 8 + 1;

        for (int i = 0; i < part; i++) {
            String str1 = null;
            if (str.length() >= 8) {
                str1 = str.substring(0, 8);
                str = str.replace(str1, "");
            } else if (str.length() < 8 && str.length() > 0) {
                str1 = str.substring(0, str.length());
                int addZero = 8 - str.length();
                for (int j = 0; j < addZero; j++) {
                    str1 = str1 + "0";
                }
            }
            System.out.println(str1);
        }
    }

    // 查看字符的ASCII的值
    public static void three() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();

            char[] chars = str.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                System.out.println(chars[i]);
                System.out.println(new String(String.valueOf(chars[i] + 0)));
            }

        }
    }

    // 将字符串转换为大写
    public static void two() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if ('a' <= chars[i] && chars[i] <= 'z') {
                System.out.println("修改之前：" + chars[i]);
                chars[i] -= 32;
                System.out.println("修改之后：" + chars[i]);
            }
        }
        System.out.println(new String(chars));
    }

    // 计算字符串个数
    public static void one() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        char[] chars = str.toCharArray();
        Set<Character> characters = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            characters.add(chars[i]);
        }

        System.out.println(characters.size());
    }
}
