package com.study.threadOrder.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch测试类
 * <p>
 * 控制线程池中线程的执行顺序原理：
 * --其工作原理是赋给CountDownLatch一个计数值，普通的任务执行完毕后，调用countDown()执行计数值减一。
 * --最后执行的任务在调用方法的开始调用await()方法，这样整个任务会阻塞，直到这个计数值（Count）为零，才会继续执行
 * <p>
 * <p>
 * 优点：操作相对简便，可以把等待线程池中任务完成后的后续工作做成任务，同样放到线程池中运行，简单来说，就是可以控制线程池中任务执行的顺序。
 * <p>
 * 缺点：
 * （1）需要提前知道任务的数量。
 * （2）大规模任务下影响并发的性能（可能）
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(7);
        CountDownLatch taskLatch = new CountDownLatch(10);
        EndTask endTask = new EndTask(taskLatch);

        executor.execute(endTask);

        for (int i = 0; i < 10; i++) {
            executor.execute(new AnyTask(taskLatch));
        }
        System.out.println("主线程已经执行结束");
    }
}
