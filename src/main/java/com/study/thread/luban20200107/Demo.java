package com.study.thread.luban20200107;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Demo implements Runnable {
    static int count = 0;
    static LunbanLock lunbanLock = new LunbanLock();

    // 偏向锁
    ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * 对象的布局--对象的组成
     * 对象头
     * 实例数据
     * 对齐填充（数据对齐）
     * <p>
     * <p>
     * jvm规范 hotspot jrockit j9 淘宝VM
     * hotspot
     * openjdk---开源项目
     * <p>
     * 对象的状态是怎么样的
     * 普通 偏向锁 轻量级锁 重量级锁 GCstate（GC标记）
     * <p>
     * hashcode存在吗？对象的地址
     */
    @Override
    public void run() {
        synchronized (lunbanLock) {
            reentrantLock.lock();
            count++;
        }
    }

    public static void main(String[] args) {

        // 打印虚拟机的一些信息
        System.out.println(System.getProperties());
//        System.out.println(lunbanLock.hashCode());
//        System.out.println(ClassLayout.parseInstance(lunbanLock).toPrintable());

        try {
            new Thread(new Demo(), "thread1").start();
            TimeUnit.SECONDS.sleep(10);
            System.out.println(ClassLayout.parseInstance(lunbanLock).toPrintable());
        }catch (Exception e){

        }

    }
}
