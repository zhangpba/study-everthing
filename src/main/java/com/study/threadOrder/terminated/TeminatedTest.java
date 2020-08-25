package com.study.threadOrder.terminated;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一 使用线程池的原生函数isTerminated();
 * <p>
 * 原理：
 * executor提供一个原生函数isTerminated()来判断线程池中的任务是否全部完成。全部完成返回true，否则返回false
 * <p>
 * 优点：操作简便；
 * 缺点：需要主线程阻塞；
 */
public class TeminatedTest {


    public static void main(String[] args) {
        try {
            ExecutorService exector = Executors.newFixedThreadPool(7);
            for (int i = 0; i < 10; i++) {
                AnyTask anyTask = new AnyTask();
                exector.execute(anyTask);
            }

            // 释放线程池
            exector.shutdown();

            while (true) {

                // executor提供一个原生函数isTerminated()来判断线程池中的任务是否全部完成。全部完成返回true，否则返回false。
                if (exector.isTerminated()) {
                    EndTask endTask = new EndTask();
                    endTask.taskRun();
                    break;
                } else {
                    Thread.sleep(3000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
