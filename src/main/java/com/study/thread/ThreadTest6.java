package com.study.thread;

/**
 * 同步 方法
 */
public class ThreadTest6 {
    public static void main(String[] args) {
        MyThread6 mt = new MyThread6();
        new Thread(mt, "票贩子A").start();// 第一个线程启动
        new Thread(mt, "票贩子B").start();// 第二个线程启动
        new Thread(mt, "票贩子C").start();// 第三个线程启动
    }
}

class MyThread6 implements Runnable {
    private int ticket = 100;

    public synchronized boolean sale() {
        if (this.ticket > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "卖票，ticket = " + this.ticket--);
            return true;
        } else {
            System.out.println("*******票已经卖光了********");
            return false;
        }
    }

    @Override
    public void run() {
        while (this.sale()) {
            ;
        }

    }
}
