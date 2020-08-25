package com.study.threadPoll.self;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * newSingleThreadExecutor这类线程池内部只有一个核心线程，
 * 以无界队列方式来执行该线程，这使得这些任务之间不需要处理线程同步的问题，
 * 它确保所有的任务都在同一个线程中按顺序中执行，并且可以在任意给定的时间不会有多个线程是活动的
 * 2019-11-27
 */
public class SingleThreadExecutorTest {
    public static void main(String[] args){
        ExecutorService mSingleThreadPool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 7; i++) {
            final int number = i;
            mSingleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("现在的时间:" + System.currentTimeMillis() + "第" + number + "个线程");
                    System.out.println(Thread.currentThread().getName());
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
