package com.study.array.queue;

/**
 * 测试队列信息
 */
public class QueueTest {

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        QueueProducer producer = new QueueProducer(queue);
        QueueProducer producer1 = new QueueProducer(queue);
        new Thread(producer).start();
        new Thread(producer1).start();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
        QueueConsumer consumer = new QueueConsumer(queue);
        QueueConsumer consumer1 = new QueueConsumer(queue);
        new Thread(consumer).start();
        new Thread(consumer1).start();
    }
}
