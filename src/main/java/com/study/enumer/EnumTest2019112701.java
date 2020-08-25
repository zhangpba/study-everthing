package com.study.enumer;

import java.util.Arrays;

public class EnumTest2019112701 {

    // 一 枚举类型的使用
    public static void doit(ColorEnum01 colorEnum01) {
        switch (colorEnum01) {
            case RED:
                System.out.println("this is RED");
                break;
            case BLUE:
                System.out.println("this is BLUE");
                break;
            case GREEN:
                System.out.println("this is GREEN");
                break;
            default:
                break;
        }
    }

    // 二 使用枚举类型中的values()方法获取枚举类型中的成员变量
    public static void showEnum() {
        System.out.println("方式一");
        for (int i = 0; i < ColorEnum01.values().length; i++) {
            // 将枚举类打印出来
            System.out.println("枚举成员变量:" + ColorEnum01.values()[i]);
        }

        System.out.println("方式二");
        for (ColorEnum01 c : ColorEnum01.values()) {
            // 将枚举类打印出来
            System.out.println("枚举成员变量:" + c);
        }

        System.out.println("方式三");
        Arrays.asList(ColorEnum01.values()).forEach(color -> System.out.println("枚举成员变量:" + color));

    }

    // 三 枚举中valueOf()与compareTo()方法的使用
    // 定义比较枚举类型方法，参数类型为枚举类型
    public static void compare(ColorEnum01 color) {
        for (int i = 0; i < ColorEnum01.values().length; i++) {
            // 调用compareTo()方法返回的结果，
            // 正值代表方法中的参数在调用该方法的枚举对象位置之前；
            // 0代表两个相互比较的枚举成员的位置相同；
            // 负值代表方法中参数在调用该方法的枚举对象位置之后
            System.out.println(color + "与" + ColorEnum01.values()[i] + "的比较结果为：" + color.compareTo(ColorEnum01.values()[i]));
        }
    }

    // 四 枚举中ordinal()方法:得到枚举成员的位置索引
    public static void getIndex() {
        for (int i = 0; i < ColorEnum01.values().length; i++) {
            // 在循环中获取枚举类型成员的索引位置
            System.out.println(ColorEnum01.values()[i] + "在枚举类型中位置索引值" + ColorEnum01.values()[i].ordinal());
        }
    }

    public static void main(String[] args) {
        // doit(ColorEnum01.BLUE);

        // showEnum();

        // ColorEnum01 c = ColorEnum01.valueOf("BLUE");
        // compare(c);

        getIndex();
    }
}
