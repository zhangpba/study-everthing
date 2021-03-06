package com.study.threadPoll.luBan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 10个任务，五个线程
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        // 一 固定大小的线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        // 提供十个任务
        for (int i = 0; i < 10; i++) {
            WorkerThreadDemo workerThreadDemo = new WorkerThreadDemo("Thread :" + i);
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
