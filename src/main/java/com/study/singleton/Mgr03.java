package com.study.singleton;

/**
 * @author zhangpba
 * @description TODO
 * @date 2022/11/14
 */
public class Mgr03 {

    // 只new出一个对象
    private static Mgr03 INSTANCE;

    // 私有化构造器
    private Mgr03() {

    }

    // 给方法加锁之后，锁粒度太大，没有必要给整个方法加锁
    public static synchronized Mgr03 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(Mgr03.getInstance().hashCode())).start();
        }
    }
}
