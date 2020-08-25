package com.study.base64;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        String str = scanner.nextLine();
        String[] strs = str.replace("[", "").replace("]", "").split(",");

        for (int i = 0; i < strs.length; i++) {
            list.add(Integer.parseInt(strs[i]));
        }


        String result = "";

        boolean flag = false;

        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        int six = 0;

        // 第一位 0-2
        if (list.contains(2)) {
            one = 2;
        } else if (list.contains(1)) {
            one = 1;
        } else if (list.contains(0)) {
            one = 0;
        } else {
            result = "invalid";
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == one) {
                list.remove(i);
            }
        }

        // 第二位 0-3
        int isZero = 0;
        int isFour = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                isZero++;
            }
            if (list.get(i) == 4) {
                isFour++;
            }
        }
        if (isZero == 4 && isFour == 1) {
            two = 4;
        }
        if (list.contains(3)) {
            two = 3;
        } else if (list.contains(2)) {
            two = 2;
        } else if (list.contains(1)) {
            two = 1;
        } else if (list.contains(0)) {
            two = 0;
        } else {
            result = "invalid";
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == two) {
                list.remove(i);
            }
        }

        // 第三位 0-5
        if (list.contains(5)) {
            three = 5;
        } else if (list.contains(4)) {
            three = 4;
        } else if (list.contains(3)) {
            three = 3;
        } else if (list.contains(2)) {
            three = 2;
        } else if (list.contains(1)) {
            three = 1;
        } else if (list.contains(0)) {
            three = 0;
        } else {
            result = "invalid";
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == three) {
                list.remove(i);
            }
        }

        // 第四位 0-9
        if (list.contains(9)) {
            four = 9;
        } else if (list.contains(8)) {
            four = 8;
        } else if (list.contains(7)) {
            four = 7;
        } else if (list.contains(6)) {
            four = 6;
        } else if (list.contains(5)) {
            four = 5;
        } else if (list.contains(4)) {
            four = 4;
        } else if (list.contains(3)) {
            four = 3;
        } else if (list.contains(2)) {
            four = 2;
        } else if (list.contains(1)) {
            four = 1;
        } else if (list.contains(0)) {
            four = 0;
        } else {
            result = "invalid";
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == four) {
                list.remove(i);
            }
        }

        // 第五位 0-5
        if (list.contains(5)) {
            five = 5;
        } else if (list.contains(4)) {
            five = 4;
        } else if (list.contains(3)) {
            five = 3;
        } else if (list.contains(2)) {
            five = 2;
        } else if (list.contains(1)) {
            five = 1;
        } else if (list.contains(0)) {
            five = 0;
        } else {
            result = "invalid";
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == five) {
                list.remove(i);
            }
        }

        // 第六位 0-9
        if (list.contains(9)) {
            six = 9;
        } else if (list.contains(8)) {
            six = 8;
        } else if (list.contains(7)) {
            six = 7;
        } else if (list.contains(6)) {
            six = 6;
        } else if (list.contains(5)) {
            six = 5;
        } else if (list.contains(4)) {
            six = 4;
        } else if (list.contains(3)) {
            six = 3;
        } else if (list.contains(2)) {
            six = 2;
        } else if (list.contains(1)) {
            six = 1;
        } else if (list.contains(0)) {
            six = 0;
        } else {
            result = "invalid";
        }


        StringBuilder builder = new StringBuilder();
        builder.append(one);
        builder.append(two);
        builder.append(":");
        builder.append(three);
        builder.append(four);
        builder.append(":");
        builder.append(five);
        builder.append(six);

        result = builder.toString();


        System.out.println(result);
    }
}
