package com.study.math;

public class MathUtil {
    public static void main(String[] args) {

        // 计算m的n次方
        Math.pow(5, 3);
        // 计算平方根
        Math.sqrt(9);
        // 计算立方根
        Math.cbrt(8);
        // 计算最大值
        Math.max(2.4, 4.5);
        // 计算最小值
        Math.min(2.4, 4.5);

        System.out.println("5的3次方为：" + Math.pow(5, 3));
        System.out.println("9的平方根" + Math.sqrt(9));
        System.out.println("8的立方根" + Math.cbrt(8));
        System.out.println("最大值" + Math.max(2.4, 4.5));
        System.out.println("最小值" + Math.min(2.4, 4.5));


        // abs绝对值
        System.out.println(Math.abs(-10.4));    //10.4
        System.out.println(Math.abs(10.1));     //10.1

        // ceil天花板的意思，就是返回大的值
        System.out.println(Math.ceil(-10.1));   //-10.0
        System.out.println(Math.ceil(10.7));    //11.0
        System.out.println(Math.ceil(-0.7));    //-0.0
        System.out.println(Math.ceil(0.0));     //0.0
        System.out.println(Math.ceil(-0.0));    //-0.0
        System.out.println(Math.ceil(-1.7));    //-1.0

        // floor地板的意思，就是返回小的值
        System.out.println(Math.floor(-10.1));  //-11.0
        System.out.println(Math.floor(10.7));   //10.0
        System.out.println(Math.floor(-0.7));   //-1.0
        System.out.println(Math.floor(0.0));    //0.0
        System.out.println(Math.floor(-0.0));   //-0.0

        // random 取得一个大于或者等于0.0小于不等于1.0的随机数
        System.out.println(Math.random());  //小于1大于0的double类型的数
        System.out.println(Math.random() * 2);//大于0小于1的double类型的数
        System.out.println(Math.random() * 2 + 1);//大于1小于2的double类型的数

        /**
         * rint 四舍五入，返回double值
         * 注意.5的时候会取偶数    异常的尴尬=。=
         */
        System.out.println(Math.rint(10.1));    //10.0
        System.out.println(Math.rint(10.7));    //11.0
        System.out.println(Math.rint(11.5));    //12.0
        System.out.println(Math.rint(10.5));    //10.0
        System.out.println(Math.rint(10.51));   //11.0
        System.out.println(Math.rint(-10.5));   //-10.0
        System.out.println(Math.rint(-11.5));   //-12.0
        System.out.println(Math.rint(-10.51));  //-11.0
        System.out.println(Math.rint(-10.6));   //-11.0
        System.out.println(Math.rint(-10.2));   //-10.0

        // round 四舍五入，float时返回int值，double时返回long值
        System.out.println(Math.round(10.1));   //10
        System.out.println(Math.round(10.7));   //11
        System.out.println(Math.round(10.5));   //11
        System.out.println(Math.round(10.51));  //11
        System.out.println(Math.round(-10.5));  //-10
        System.out.println(Math.round(-10.51)); //-11
        System.out.println(Math.round(-10.6));  //-11
        System.out.println(Math.round(-10.2));  //-10
    }

}
