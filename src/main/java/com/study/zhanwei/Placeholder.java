package com.study.zhanwei;

import java.text.MessageFormat;

/**
 * 占位符
 * 2020-04-20
 */
public class Placeholder {

    public static void main(String[] args) {
        one();
        two();
    }

    // 第一种：使用%s占位，使用String.format转换
    public static void one() {
        String url = "我叫%s,今年%s岁";
        String name = "小明";
        String age = "28";

        url = String.format(url, name, age);

        System.out.println(url);
    }

    // 第二种：使用{1}占位，使用MessageFormat.format转换
    public static void two() {

        String url02 = "我叫{0},今年{1}岁。";
        String name = "小明";
        String age = "28";

        url02 = MessageFormat.format(url02, name, age);

        System.out.println(url02);
    }
}
