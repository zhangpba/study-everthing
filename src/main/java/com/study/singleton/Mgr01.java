package com.study.singleton;

/**
 * @author zhangpba
 * @description 饿汉模式
 * @date 2022/11/14
 */
public class Mgr01 {

    // 只new出一个对象
    private static final Mgr01 INSTANCE = new Mgr01();

    // 私有化构造器
    private Mgr01() {

    }

    public static Mgr01 getInstance() {
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mgr01 m1 = Mgr01.getInstance();
        Mgr01 m2 = Mgr01.getInstance();
        System.out.println(m1 == m2);
    }
}
