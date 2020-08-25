package com.study.threadPoll.luBan;

import java.util.concurrent.*;

/**
 * 自定义线程池 20190813
 */
public class ThreadPoolSelf {

    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
        // pool这个线程池专门处理队列queue
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3,
                5,
                1000,
                TimeUnit.MILLISECONDS,
                queue);
        // 提供十个任务
        for (int i = 0; i < 30; i++) {
            WorkerThreadDemo workerThreadDemo = new WorkerThreadDemo("Thread:" + i);
            // 线程池接收任务
            pool.execute(workerThreadDemo);
        }

        // 顺序关闭任务
        pool.shutdown();
        while (!pool.isTerminated()) {

        }
        System.out.println("All Thread is finshed!");
    }
}
