package com.study.threadPoll.meiTeXueYuan;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池基本使用方式 三 20190813
 */
public class ThreadPollTest4 {
    public static void main(String[] args) throws InterruptedException {
        // 可定时线程池 3个核心线程
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 100; i++) {

            final int temp = i;
            // execute方法作用：执行方法
            newScheduledThreadPool.schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ",i:" + temp);
                }
            }, 3, TimeUnit.SECONDS);
        }

    }
}
