package com.study.enumer;

public class EnumTest2019112702 {


    public static void main(String[] args) {

        for (ColorEnum02 c : ColorEnum02.values()) {
            System.out.println("枚举成员：" + c + " 值：" + c.getValue() + " 描述：" + c.getDescription());
        }

        System.out.println("值转换成枚举：" + ColorEnum02.valueOf(2));
        System.out.println("字符转换为枚举：" + ColorEnum02.valueOf("GREEN"));

    }
}
