package com.study.thread;

public class ThreadTest3 {
    public static void main(String[] args) {
        MyThread3 mt = new MyThread3();
        new Thread(mt).start();// 第一个线程启动
        new Thread(mt).start();// 第二个线程启动
        new Thread(mt).start();// 第三个线程启动
    }
}

class MyThread3 implements Runnable {
    private int ticket = 5;

    @Override
    public void run() {
        for (int x = 0; x < 100; x++) {
            if (this.ticket > 0) {
                System.out.println("卖票，ticket = " + this.ticket--);
            }

        }
    }
}
