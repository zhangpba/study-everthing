package com.study.singleton;

/**
 * @author zhangpba
 * @description 双重检查 DCL（Double Check Lock）
 * @date 2022/11/14
 */
public class Mgr05 {

    // 只new出一个对象  volatile防止CPU执行汇编指令的时候发生重排序
    private static volatile Mgr05 INSTANCE;

    // 私有化构造器
    private Mgr05() {

    }

    // 给方法加锁之后，锁粒度太大，没有必要给整个方法加锁
    public static Mgr05 getInstance() {
        if (INSTANCE == null) {
            // 线程1进入这个地方后（判为空，但是还未创建对象），线程二也开始执行，此时线程二创建一个对象，线程二执行结束后，线程一也创建了一个对象
            // 所以进行双重检查  类似于CAS(自旋锁、乐观锁)
            synchronized (Mgr05.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr05();
                }
            }
        }
        return INSTANCE;
    }

    public void m() {
        System.out.println("m");
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(Mgr05.getInstance().hashCode())).start();
        }
    }
}
