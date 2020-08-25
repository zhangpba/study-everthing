package com.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * runnable和Callable的区别
 * <p>
 * 1 Runnable是jdk1.0的时候提供的多线程实现接口，而Callable是jdk1.5之后提出来的
 * 2 java.lang.Runnable接口之中只提供有个run()方法，并且没有返回值
 * 3 Java.util.concurrent.Callable接口提供call()方法，可以有返回值
 */
public class CallableTest {
    public static void main(String[] args) throws Exception {

        // FutureTask<>里面的泛型可以是任意的类
        FutureTask<String> task = new FutureTask<>(new MyThread4());
        // 启动线程
        new Thread(task).start();
        System.out.println("[线程返回数据] : " + task.get());
    }
}

/**
 * Callable实现多线程
 */
class MyThread4 implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int x = 0; x < 10; x++) {
            System.out.println("**********执行线程，x = " + x);
        }
        return "线程执行结束";
    }
}
