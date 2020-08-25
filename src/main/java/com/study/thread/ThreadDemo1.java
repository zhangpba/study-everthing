package com.study.thread;

/**
 * 生产者
 */
class Producer1 implements Runnable {
    private Message1 msg;

    public Producer1(Message1 msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        for (int x = 0; x < 100; x++) {
            if (x % 2 == 0) {
                this.msg.set("王建", "宇宙大帅哥");
            } else {
                this.msg.set("小高", "猥琐第一人，常态保持");
            }
        }
    }
}

/**
 * 消费者
 */
class Consumer1 implements Runnable {
    private Message1 msg;

    public Consumer1(Message1 msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        for (int x = 0; x < 100; x++) {
            System.out.println(this.msg.get());
        }
    }
}

/**
 * 同步的处理交给message
 */
class Message1 {
    private String title;
    private String content;

    public synchronized void set(String title, String content) {
        this.title = title;
        // 增加延迟
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.content = content;
    }

    public synchronized String get() {
        // 增加延迟
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.title + " - " + this.content;
    }
}

/**
 * 线程同步
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        Message1 message = new Message1();
        new Thread(new Producer1(message)).start();// 生产者线程
        new Thread(new Consumer1(message)).start();// 消费者线程
    }

}
