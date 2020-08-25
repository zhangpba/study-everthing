package com.study.threadPoll.luBan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 10个任务
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
        // 二 可变大小的线程池，没有上限(有多少任务，就产生多少线程)
        ExecutorService pool = Executors.newCachedThreadPool();
        // 提供十个任务
        for (int i = 0; i < 10; i++) {
            WorkerThreadDemo workerThreadDemo = new WorkerThreadDemo("Thread=" + i);
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
