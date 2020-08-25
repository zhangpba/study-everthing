package com.study.threadPoll.meiTeXueYuan;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池 20190813
 */
public class ThreadPollTest6 {
    public static void main(String[] args) throws InterruptedException {

        // 这个线程池表达的含义 核心线程1 最多创建线程2
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(3));

        // 任务1线程创建线程并执行
        threadPoolExecutor.execute(new TaskThread("任务1"));
        // 任务2存放在LinkedBlockingQueue
        threadPoolExecutor.execute(new TaskThread("任务2"));
        // 任务3存放在LinkedBlockingQueue
        threadPoolExecutor.execute(new TaskThread("任务3"));
        // 任务4存放在LinkedBlockingQueue
        threadPoolExecutor.execute(new TaskThread("任务4"));

        // 任务2,3,4都存放在缓存队列中
        // 判断 创实际建线程数>2 任务5存放在LinkedBlockingQueue

        // 任务5存放在LinkedBlockingQueue
        threadPoolExecutor.execute(new TaskThread("任务5"));
        // 任务6存放在LinkedBlockingQueue
//        threadPoolExecutor.execute(new TaskThread("任务6"));

    }
}

// 线程执行的任务
class TaskThread implements Runnable {
    private String taskName;

    public TaskThread(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + taskName);
    }
}
