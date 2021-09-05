package com.study.array.queue;

/**
 * 队列：用java写一个队列
 * 参考资料：https://blog.csdn.net/SnailMann/article/details/80549382
 *
 * @author zhangpba
 * @date 2021-09-05
 */
public interface QueueApi<T> {

    boolean isEmpty();

    int length();

    void enQueue(T t) throws Exception;

    T deQueue() throws Exception;
}
