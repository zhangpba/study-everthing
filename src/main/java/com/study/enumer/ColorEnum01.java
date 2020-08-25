package com.study.enumer;

/**
 * 以往设置常量，通常将常量放置在接口中，这样在程序中就可以直接使用了，
 * 并且该常量不能被修改，因为在接口中定义的常量时，该常量的修饰符为final与static
 * <p>
 * 枚举类型的常用方法
 * 方法名称	    具体含义	                                使用方法
 * values()	    该方法可以将枚举类型成员以数组的形式返回	    枚举类型名称.values()
 * valueOf()	该方法可以实现将普通字符串转换为枚举实例	    枚举类型名称.valueOf("ABC")
 * compareTo()	该方法用于比较两个枚举对象在定义时的顺序	    枚举对象.compareTo()
 * ordinal()	该方法用于得到枚举成员的位置索引	        枚举对象.ordinal()
 */
public enum ColorEnum01 {
    RED,
    BLUE,
    GREEN
}
