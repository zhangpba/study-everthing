package com.study.singleton;

/**
 * @author zhangpba
 * @description TODO
 * @date 2022/11/14
 */
public class Mgr02 {

    // 只new出一个对象
    private static Mgr02 INSTANCE;

    // 私有化构造器
    private Mgr02() {

    }

    // 这种饱汉模式线程不安全
    public static Mgr02 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr02();
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(Mgr02.getInstance().hashCode())).start();
        }
    }
}
