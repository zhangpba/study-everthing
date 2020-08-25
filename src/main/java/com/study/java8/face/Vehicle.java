package com.study.java8.face;

public interface Vehicle {
    default void print() {
        System.out.println("我是一辆车!");
    }

    // 静态方法
    static void blowHorn() {
        System.out.println("按喇叭!!!");
    }
}


interface FourWheeler {
    default void print() {
        System.out.println("我是一辆四轮车!");
    }
}



