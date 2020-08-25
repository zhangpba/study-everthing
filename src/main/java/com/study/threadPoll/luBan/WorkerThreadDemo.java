package com.study.threadPoll.luBan;

/**
 * 任务模型
 */
public class WorkerThreadDemo implements Runnable {

    private String info;

    public WorkerThreadDemo(String info) {
        this.info = info;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started. 任务 is:" + this.info);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "finished");
    }

    @Override
    public String toString() {
        return this.info;
    }
}
