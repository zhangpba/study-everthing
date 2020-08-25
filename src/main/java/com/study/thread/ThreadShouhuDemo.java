package com.study.thread;

/**
 * 1 守护线程都是围绕在用户线程的周围，如果程序执行完毕，守护线程也就结束，
 * 2 JVM中最大的守护线程是GC线程
 */
public class ThreadShouhuDemo {
    public static void main(String[] args) throws Exception {
        Thread userThread = new Thread(() -> {
            for (int x = 0; x < 10; x++) {
                try {
                    Thread.sleep(100);// 为了容易看，加一点儿延迟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "正在运行，x=" + x);
            }
        }, "用户线程");

        Thread daeThread = new Thread(() -> {
            for (int x = 0; x < Integer.MAX_VALUE; x++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在运行，x=" + x);
            }
        }, "守护线程");

        daeThread.setDaemon(true);//设置为守护线程
        userThread.start();
        daeThread.start();
    }
}
