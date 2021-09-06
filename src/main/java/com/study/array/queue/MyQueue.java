package com.study.array.queue;

/**
 * 队列实现
 *
 * @author zhangpba
 * @date 2021-09-06
 */
public class MyQueue {

    /**
     * 队列
     */
    private Object[] elements;

    public MyQueue() {
        elements = new Object[0];// 初始化数组大小为0
    }

    // 进队  把每个进队的元素都放到最后一位 并复制原数组
    public synchronized void inQueue(Object element) {
        Object[] newArr = new Object[elements.length + 1];
        newArr[elements.length] = element;
        for (int i = 0; i < elements.length; i++) {
            newArr[i] = elements[i];
        }
        elements = newArr;
    }

    // 出队  取出列队里的第一个元素  并复制原数组 从索引1开始(因为第一个元素已经取出了)
    public Object outQueue() {
        if (elements.length == 0) {
//            throw new RuntimeException("队列为空！");
            return "队列为空！";
        }
        Object element = elements[0];
        Object[] newArr = new Object[elements.length - 1];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = elements[i + 1];
        }
        elements = newArr;
        return element;
    }

    // 查看列队第一个元素
    public synchronized Object getFirst() {
        if (elements.length == 0) {
            throw new RuntimeException("队列为空！");
        }
        return elements[elements.length - 1];
    }

    // 判断列队是否为空
    public boolean isEmpty() {
        return elements.length == 0;
    }

    // 获取队列大小
    public int getLength() {
        return elements.length;
    }

    /**
     * 重写toString方法
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("{");
        if (elements.length == 0) {
            return "{}";
        } else {
            for (int i = 0; i < elements.length; i++) {
                buffer.append(elements[i]);
                if (i < elements.length - 1) {
                    buffer.append(",");
                }
            }
            buffer.append("}");
            return buffer.toString();
        }
    }

    /**
     * 测试队列的逻辑
     *
     * @param args
     */
    public static void main(String[] args) {
        // 创建序列,并增加10个元素
        MyQueue queue = new MyQueue();
        for (int i = 0; i < 10; i++) {
            queue.inQueue(i);
        }
        System.out.println("创建十个元素的队列：" + queue.toString());
        System.out.println("创建十个元素的队列大小：" + queue.getLength());

        // 消费对列中的数据3个元素
        queue.outQueue();
        queue.outQueue();
        queue.outQueue();
        System.out.println("消费三个元素后的队列：" + queue.toString());
        System.out.println("消费三个元素后的队列长度：" + queue.getLength());

        // 再给对列增加5个元素
        queue.inQueue(10);
        queue.inQueue(11);
        queue.inQueue(12);
        queue.inQueue(13);
        queue.inQueue(14);

        System.out.println("再次增加五个元素后的队列：" + queue.toString());
        System.out.println("再次增加五个元素后的队列大小：" + queue.getLength());
    }
}
