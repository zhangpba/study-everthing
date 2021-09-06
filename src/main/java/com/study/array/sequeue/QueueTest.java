package com.study.array.sequeue;

/**
 * 队列测试
 *
 * @author zhangpba
 * @date 2021-09-05
 */
public class QueueTest {

    public static void main(String[] args) throws Exception {
        QueueApi<Integer> queue = new SeqQueue(10);
//        QueueApi<Integer> queue = new SeqCycleQueue<>(10);
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);
        queue.enQueue(6);
        System.out.println(queue);
        System.out.println(queue.length());
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        System.out.println(queue);
        System.out.println(queue.length());
        queue.enQueue(7);
        queue.enQueue(8);
        queue.enQueue(9);
        queue.enQueue(10);
        System.out.println(queue);
        System.out.println(queue.length());
        queue.enQueue(11);
    }
}
