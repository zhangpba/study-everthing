package com.study.thread;

public class ThreadTest1 {

    public static void main(String[] args) {
        MyThread myThread = new MyThread("线程A");

        // 同一个线程智能启动一次，不能启动两次
        myThread.start();
        myThread.start();
    }
}


class MyThread extends Thread {
    private String title;

    public MyThread(String title) {
        this.title = title;
    }

    @Override
    public void run() {
        for (int x = 0; x < 10; x++) {
            System.out.println(this.title + "运行，x=" + x);
        }
    }
}