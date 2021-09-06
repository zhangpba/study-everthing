package com.study.array.queue;

import com.study.array.queue.MyQueue;

/**
 * 消费者：消费队列中的数据
 *
 * @author zhangpba
 * @date 2021-09-06
 */
public class QueueConsumer implements Runnable {

    private MyQueue queue;

    public QueueConsumer(MyQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("队列：" + queue.toString());
        System.out.println("队列大小：" + queue.getLength());

        // 将消费的数据信息打印出来，方便观察
        int size = queue.getLength();
        for (int i = 0; i < size - 1; i++) {
            System.out.println(Thread.currentThread().getName() + "消费：" + queue.outQueue());
        }
    }
}
