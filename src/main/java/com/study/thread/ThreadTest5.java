package com.study.thread;

/**
 * 同步 代码块
 */
public class ThreadTest5 {
    public static void main(String[] args) {
        MyThread5 mt = new MyThread5();
        new Thread(mt, "票贩子A").start();// 第一个线程启动
        new Thread(mt, "票贩子B").start();// 第二个线程启动
        new Thread(mt, "票贩子C").start();// 第三个线程启动
    }
}

class MyThread5 implements Runnable {
    private int ticket = 10000;

    @Override
    public void run() {
        while (true)
            synchronized (this) { // 每次只允许一个线程进行访问
                if (this.ticket > 0) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖票，ticket = " + this.ticket--);
                } else {
                    System.out.println("*******票已经卖光了********");
                    break;
                }
            }

    }
}
