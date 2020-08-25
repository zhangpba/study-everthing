package com.study.enumer;

public class EnumTest2019112703 {

    public static void main(String[] args) {
        for (ColorInterface color : ColorEnum03.values()) {
            System.out.println("枚举成员：" + color + "值：" + color.getValue() + "描述：" + color.getDescription());
        }
    }
}

/**
 * 枚举类型声明提供了一种用户友好的变量定义方法，枚举了某种数据类型所有可能出现的值。总结枚举类型，它具有以下特点：
 *
 * （1）类型安全。
 * （2）紧凑有效的数据定义。
 * （3）可以和程序其他部分完美交互。
 * （4）运行效率高。
 *
 * https://blog.csdn.net/pan_junbiao/article/details/85257445
 */
