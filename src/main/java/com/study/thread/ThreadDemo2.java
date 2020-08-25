package com.study.thread;

/**
 * 生产者
 */
class Producer2 implements Runnable {
    private Message2 msg;

    public Producer2(Message2 msg) {
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
class Consumer2 implements Runnable {
    private Message2 msg;

    public Consumer2(Message2 msg) {
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
class Message2 {
    private String title;
    private String content;
    private boolean flag;// 表示生产或者消费形式

    // flag = true：允许生产，但是不允许消费
    // flag = false: 允许消费，不允许生产
    public synchronized void set(String title, String content) {
        if (!this.flag) {// 无法进行生产，应该等待被消费
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.title = title;
        // 增加延迟
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.content = content;
        this.flag = false;//已经生产过了
        super.notify();// 唤醒等待线程
    }

    public synchronized String get() {
        if (this.flag == true) {//还未生产，需要等待
            try {
                super.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 增加延迟
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            return this.title + " - " + this.content;
        } finally {// 不管如何都要执行
            this.flag = true;// 继续生产
            super.notify();// 唤醒等待线程
        }

    }
}


public class ThreadDemo2 {
    public static void main(String[] args) {
        Message2 message = new Message2();
        new Thread(new Producer2(message)).start();// 生产者线程
        new Thread(new Consumer2(message)).start();// 消费者线程
    }

}
