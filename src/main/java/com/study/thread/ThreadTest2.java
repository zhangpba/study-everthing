package com.study.thread;

/**
 * 多线程的实现，优先考虑用Runnable接口实现，可以避免单继承的局限，
 * 并且永恒都是通过Thread类对象启动线程。
 */
public class ThreadTest2 {

    public static void main(String[] args) {
        MyThread2 myThread = new MyThread2("线程A");

        Thread threadA = new Thread(new MyThread2("线程A"));
        Thread threadB = new Thread(new MyThread2("线程B"));
        Thread threadC = new Thread(new MyThread2("线程C"));

        threadA.start();
        threadB.start();
        threadC.start();

    }
}

/**
 * 通过接口实现线程。
 * 由于不用再继承Thread父类了， 那么对于此时的myThread类中也就不在有start()方法了，无法进行多线程启动。
 * 所以用Thread的构造函数public Thread(Runnable target)，把runnable传给Thread然后，调用start()方法
 */
class MyThread2 implements Runnable {
    private String title;

    public MyThread2(String title) {
        this.title = title;
    }

    @Override
    public void run() {
        for (int x = 0; x < 10; x++) {
            System.out.println(this.title + "运行，x=" + x);
        }
    }
}