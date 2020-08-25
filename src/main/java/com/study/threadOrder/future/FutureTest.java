package com.study.threadOrder.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future判断任务执行状态
 * <p>
 * 原理：
 * 使用submit向线程池提交任务与execute提交不同，submit会有Future类型的返回值。通过返回返回值可以用于判断线程任务的执行状态。
 */
public class FutureTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(7);
        AnyTask anyTask = new AnyTask();
        // 线程池的submit提交有返回值
        Future<?> future = executor.submit(anyTask);

        while (true) {
            if (future.isDone()) {
                break;
            }
            System.out.println("主线程等待...");
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("主线程已经执行完了");

    }
}
