package com.study.threadOrder.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 最后执行的任务
 */
public class EndTask implements Runnable {

    private CountDownLatch endTaskLatch;

    public EndTask(CountDownLatch endTaskLatch) {
        this.endTaskLatch = endTaskLatch;
    }

    @Override
    public void run() {
        try {
            // 调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
            endTaskLatch.await();
            System.out.println("开始执行最终任务");
            System.out.println("EndTask-count" + endTaskLatch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
