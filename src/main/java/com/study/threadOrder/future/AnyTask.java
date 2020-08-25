package com.study.threadOrder.future;

/**
 * 普通任务
 */
public class AnyTask implements Runnable {

    @Override
    public void run() {
        try {
            int i = 5;
            while (i >= 0) {
                System.out.println("正在执行的线程！i=" + i);
                Thread.sleep(10000);
                i--;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
