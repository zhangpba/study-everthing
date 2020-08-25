package com.study.threadPoll.meiTeXueYuan;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列 20190813
 */
public class ThreadPollTest1 {
    public static void main(String[] args) throws InterruptedException {
        // 阻塞队列
        ArrayBlockingQueue<Object> arrayBlockingQueue = new ArrayBlockingQueue<Object>(3);
        // 没有加超时时间都为阻塞队列
        arrayBlockingQueue.offer("zhangsan");
        arrayBlockingQueue.offer("lisi");

        arrayBlockingQueue.offer("yushengjun", 2, TimeUnit.SECONDS);
        // 等待2秒钟
        arrayBlockingQueue.offer("15k", 2, TimeUnit.SECONDS);
        //获取队列信息，并且会删除队列
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.size());
    }
}
