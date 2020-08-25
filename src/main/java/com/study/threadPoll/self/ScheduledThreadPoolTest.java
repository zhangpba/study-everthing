package com.study.threadPoll.self;

import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * newScheduledThreadPool 创建一个线程池，
 * 它的核心线程数量是固定的，而非核心线程数是没有限制的，并且当非核心线程闲置时会被立即回收，
 * 它可安排给定延迟后运行命令或者定期地执行。这类线程池主要用于执行定时任务和具有固定周期的重复任务。
 * 2019-11-27
 */
public class ScheduledThreadPoolTest {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        //设置池中核心数量是2
        ScheduledExecutorService mScheduledThreadPool = Executors.newScheduledThreadPool(2);
        System.out.println("现在的时间:" + System.currentTimeMillis()+" "+simpleDateFormat.format(System.currentTimeMillis()));
        start1(mScheduledThreadPool);
    }

    // 延迟4秒执行
    public static void start1(ScheduledExecutorService mScheduledThreadPool){
        mScheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程的时间:" + System.currentTimeMillis()+" "+simpleDateFormat.format(System.currentTimeMillis()));
            }
        }, 4, TimeUnit.SECONDS);//这里设置延迟4秒执行
    }

    // 延迟2秒后每3秒执行一次
    public static void start2(ScheduledExecutorService mScheduledThreadPool){
        mScheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程的时间:"+System.currentTimeMillis()+" "+simpleDateFormat.format(System.currentTimeMillis()));
            }
        }, 2, 3,TimeUnit.SECONDS);//这里设置延迟2秒后每3秒执行一次

    }

}
