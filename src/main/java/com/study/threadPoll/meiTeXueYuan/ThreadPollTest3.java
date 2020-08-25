package com.study.threadPoll.meiTeXueYuan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池基本使用方式 二 20190813
 */
public class ThreadPollTest3 {
    public static void main(String[] args) throws InterruptedException {
        // 可固定长度线程池 核心线程数为3 最多创建3个线程
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) {
            final int temp = i;
            // execute方法作用：执行方法
            newFixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ",i:" + temp);
                }
            });
        }

    }
}
