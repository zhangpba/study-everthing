package com.study.array.queue;

import com.study.array.queue.MyQueue;

/**
 * 生产者：生产队列中的数据
 *
 * @author zhangpba
 * @date 2021-09-06
 */
public class QueueProducer implements Runnable {

    private MyQueue queue;

    public QueueProducer(MyQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            queue.inQueue("线程名：" + Thread.currentThread().getName() + "数据" + i);
        }
    }
}
