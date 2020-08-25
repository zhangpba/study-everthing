package com.study.threadPoll.self;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * newCachedThreadPool,是一种线程数量不定的线程池，
 * 并且其最大线程数为Integer.MAX_VALUE，这个数是很大的，一个可缓存线程池，如果线程池长度超过处理需要，
 * 可灵活回收空闲线程，若无可回收，则新建线程。但是线程池中的空闲线程都有超时限制，
 * 这个超时时长是60秒，超过60秒闲置线程就会被回收。调用execute将重用以前构造的线程(如果线程可用)。
 * 这类线程池比较适合执行大量的耗时较少的任务，当整个线程池都处于闲置状态时，线程池中的线程都会超时被停止。
 * 2019-11-27
 */
public class CachedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService mCachelThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 7; i++) {
            final int index = i;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mCachelThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("第" + index + "个线程" + Thread.currentThread().getName());
                }
            });

        }


    }
}
