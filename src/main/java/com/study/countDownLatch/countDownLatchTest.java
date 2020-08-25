package com.study.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class countDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);
        System.out.println("主线程开始执行......");

        // 第一个子线程执行
        ExecutorService service1 = Executors.newSingleThreadExecutor();
        service1.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });

        service1.shutdown();


        // 第二个子线程执行
        ExecutorService service2 = Executors.newSingleThreadExecutor();
        service2.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                latch.countDown();
            }
        });

        service2.shutdown();

        System.out.println("等待两个子线程都执行完毕......");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程");
    }

    /**

     1.概念：CountDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
     2.源码：
     是通过一个计数器来实现的，计数器的初始值是线程的数量。
     每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，
     然后在闭锁上等待的线程就可以恢复工作了。

     2.1 CountDownLatch类中只提供了一个构造器：

     //参数count为计数值
     public CountDownLatch(int count) {  };


     2.2 类中有三个方法是最重要的：

     //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
     public void await() throws InterruptedException { };
     //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
     public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };
     //将count值减1
     public void countDown() { };

     */
}
