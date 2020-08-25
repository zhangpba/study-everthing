package com.study.threadPoll.self;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * newFixedThreadPool 创建一个指定工作线程数量的线程池，
 * 每当提交一个任务就创建一个工作线程，当线程 处于空闲状态时，它们并不会被回收，
 * 除非线程池被关闭了，如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列（没有大小限制）中。
 * 由于newFixedThreadPool只有核心线程并且这些核心线程不会被回收，这样它更加快速底相应外界的请求
 * 2019-11-27
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        //设置最大线程数5个
        ExecutorService mFixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 7; i++) {
            final int index = i;
            mFixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("时间是:" + System.currentTimeMillis() + "第" + index + "个线程" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

}
