package com.study.thread;

public class ThreadStopDemo {

    public static boolean flag = true;

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            long num = 0;
            while (flag) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在运行，num = " + num++);
            }
        }, "执行线程 ").start();
        Thread.sleep(200);// 运行200毫秒
        flag = false;// 停止线程
    }
}
