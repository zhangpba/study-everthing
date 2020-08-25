package com.study.threadOrder.terminated;

/**
 * 普通任务线程
 */
public class AnyTask implements Runnable {
    @Override
    public void run() {

        try {
            Thread.sleep(3000);
            System.out.println("普通线程执行结束！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
