package com.study.threadOrder.jishu;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 普通任务
 */
public class AnyTask implements Runnable {

    // 普通任务计数
    public static int taskNum = 0;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {

        try {
            Thread.sleep(3000);
            System.out.println("普通任务执行完毕");

            lock.lock();
            AnyTask.taskNum++;
            System.out.println("AnyTask taskNum = "+ taskNum);
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
