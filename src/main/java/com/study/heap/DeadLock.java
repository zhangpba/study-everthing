package com.study.heap;

/**
 * 死锁线程
 *
 * @author zhangpba
 * @date 2021-11-01
 */
public class DeadLock {

    private Object lockA = new Object();
    private Object lockB = new Object();

    private void deadLock() {
        // 定义线程t1,锁定A变量睡眠2秒去抢占B的资源
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("进入线程t1");
                synchronized (lockA) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockB) {
                        System.out.println(Thread.currentThread().getName());
                    }
                }
            }
        }, "thread-zhangpba-1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("进入线程t2");
                synchronized (lockB) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockA) {
                        System.out.println("线程名:" + Thread.currentThread().getName());
                    }
                }
            }
        }, "thread-zhangpba-1");

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        new DeadLock().deadLock();
    }
}
