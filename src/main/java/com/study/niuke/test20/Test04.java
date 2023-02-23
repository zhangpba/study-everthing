package com.study.niuke.test20;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ4 字符串分隔
 * @date 2023/2/21
 */
public class Test04 {

    /**
     * 描述
     * •输入一个字符串，请按长度为8拆分每个输入字符串并进行输出；
     * <p>
     * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
     * 输入描述：
     * 连续输入字符串(每个字符串长度小于等于100)
     * <p>
     * 输出描述：
     * 依次输出所有分割后的长度为8的新字符串
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String info = scanner.nextLine();
        process02(info);

    }

    private static void process01(String info) {
        List<String> list = new ArrayList<>();
        int num = 0;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < info.length(); i++) {
            num++;
            buffer.append(String.valueOf(info.charAt(i)));
            if (num == 8) {
                list.add(buffer.toString());
                buffer = new StringBuffer();
                num = 0;
            }
        }

        if (buffer.length() < 8 && buffer.length() > 0) {
            StringBuffer zero = new StringBuffer();
            for (int i = 0; i < 8 - buffer.length(); i++) {
                zero.append("0");
            }

            buffer.append(zero);
            list.add(buffer.toString());
        }


        for (String str : list) {
            System.out.println(str);
        }
    }

    private static void process02(String info) {

        while (info.length() > 8) {
            System.out.println(info.substring(0, 8));
            info = info.substring(8);
        }

        while (info.length() > 0 && info.length() < 8) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < 8 - info.length(); i++) {
                stringBuffer.append(0);
            }

            System.out.println(info + stringBuffer);
        }
    }
}
