package com.study.threadOrder.countDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 普通线程任务
 */
public class AnyTask implements Runnable {

    private CountDownLatch endTaskLatch;

    public AnyTask(CountDownLatch endTaskLatch) {
        this.endTaskLatch = endTaskLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("普通任务执行完毕！");
            // 将count减1
            endTaskLatch.countDown();
            System.out.println("AnyTask-count"+endTaskLatch.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
