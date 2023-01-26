package com.study.singleton;

/**
 * @author zhangpba
 * @description TODO
 * @date 2022/11/14
 */
public class Mgr04 {

    // 只new出一个对象
    private static Mgr04 INSTANCE;

    // 私有化构造器
    private Mgr04() {

    }

    // 给方法加锁之后，锁粒度太大，没有必要给整个方法加锁
    public static Mgr04 getInstance() {
        if (INSTANCE == null) {
            // 线程1进入这个地方后（判为空，但是还未创建对象），线程二也开始执行，此时线程二创建一个对象，线程二执行结束后，线程一也创建了一个对象
            synchronized (Mgr04.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Mgr04();
            }
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(Mgr04.getInstance().hashCode())).start();
        }
    }
}
