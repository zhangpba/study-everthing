package com.study.threadPoll.meiTeXueYuan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * 线程池基本使用方式 四 20190813
 */
public class ThreadPollTest5 {
    public static void main(String[] args) throws InterruptedException {
        // 单线线程
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            final int temp = i;
            // execute方法作用：执行方法
            newSingleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ",i:" + temp);
                }
            });
        }

    }
}
