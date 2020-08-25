package com.study.threadOrder.jishu;

/**
 * 最终任务
 * <p>
 * 原理：实行起来基本上和CountDownLatch 的原理差不多
 * --所有的普通任务维持一个static的计数器，当任务完成时计数器加一（这里要用锁），当计数器的值等于任务数时，这时所有的任务已经执行完毕了，这时endTask就会自动执行。
 * <p>
 * --所有的任务，包括endTask都可以放到线程池中，当普通任务未执行完毕时，如果endTask开始执行，也会一直阻塞等待，直到公共计数等于等于任务数
 * <p>
 * <p>
 * 优点：灵活、可控
 * 缺点：需要预知运算的任务数、操作相对有点繁琐
 */
public class EndTask implements Runnable {
    private int endNum = 0;

    public EndTask(int endNum) {
        this.endNum = endNum;
    }

    @Override
    public void run() {

        try {
            while (true) {
                if (endNum == AnyTask.taskNum) {
                    System.out.println("开始执行最终任务！");
                    break;
                } else {
                    System.out.println("线程池的任务还没有执行结束，等待中...");
                    Thread.sleep(3000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
