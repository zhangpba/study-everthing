package com.study.enumer;

/**
 * 枚举类型中的构造方法
 */
public enum ColorEnum02 {
    RED(1, "我是红色"),
    BLUE(2, "我是蓝色"),
    GREEN(3, "我是绿色");

    private final int value;
    private final String description;

    // 只写get方法，不需要set方法
    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    // 构造器
    ColorEnum02(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static ColorEnum02 valueOf(int value) {
        switch (value) {
            case 1:
                return ColorEnum02.RED;
            case 2:
                return ColorEnum02.BLUE;
            case 3:
                return ColorEnum02.GREEN;
            default:
                return null;
        }
    }

}
