package com.study.threadPoll.luBan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 10个任务
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        // 四 可调度的线程池
        ExecutorService pool = Executors.newScheduledThreadPool(3);
        // 提供十个任务
        for (int i = 0; i < 10; i++) {
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
