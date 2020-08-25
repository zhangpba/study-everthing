package com.study.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试锁
 * 2020-07-07
 */
public class TestLock {

    private static final Lock lock = new ReentrantLock();

    public static void work() {

        // 加锁
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // 定义线程任务
        Runnable runnable = () -> {
            work();
        };

        // 两个线程
        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");

        t1.start();
        t2.start();

        /**
         * t1获取锁，休息5秒钟，释放锁，t2才能获取锁
         */
    }
}
