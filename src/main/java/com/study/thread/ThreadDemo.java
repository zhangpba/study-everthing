package com.study.thread;

/**
 * 生产者
 */
class Producer implements Runnable {
    private Message msg;

    public Producer(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        for (int x = 0; x < 100; x++) {
            if (x % 2 == 0) {
                this.msg.setTitle("王建");
                // 增加延迟
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.msg.setContent("宇宙大帅哥");
            } else {
                this.msg.setTitle("小高");
                // 增加延迟
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.msg.setContent("猥琐第一人，常态保持");
            }
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable {
    private Message msg;

    public Consumer(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        for (int x = 0; x < 100; x++) {
            // 增加延迟
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.msg.getTitle() + " - " + this.msg.getContent());
        }
    }
}

class Message {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

/**
 * 利用线程模拟生产者与消费者
 */
public class ThreadDemo {
    public static void main(String[] args) {
        Message message = new Message();
        new Thread(new Producer(message)).start();// 生产者线程
        new Thread(new Consumer(message)).start();// 消费者线程
    }

}
