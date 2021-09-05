package com.study.array.queue;

/**
 * 非循环顺序队列
 * 队头对应数组头，队尾对应数组尾
 *
 * @date 2021-09-05
 */
public class SeqQueue<T> implements QueueApi<T> {

    private Object[] element;   // 存放数据的数组
    private int head;   // 头下标，存放队头在数组的位置
    private int tail;   // 尾下标，存放队尾在数组的位置
    private int length; // 队列长度
    private int size;   // 队列可以使用的最大容量

    /**
     * 顺序队列的构造函数
     *
     * @param size
     */
    public SeqQueue(int size) {
        this.element = new Object[size];    // 初始化大小
        this.head = -1;                     // 因为是空的，头下标和为下标都为-1
        this.tail = -1;
        this.length = 0;
        this.size = size;
    }

    /**
     * 是否为空队列
     *
     * @return
     */
    public boolean isEmpty() {
        return this.head == this.tail; // 如果头下标==尾下标，则表示空表
    }

    /**
     * 返回队列的长度
     *
     * @return
     */
    public int length() {
        return this.length;
    }

    /**
     * 进队，从队尾进队
     */
    public void enQueue(T t) throws Exception {
        if (isEmpty()) {
            this.head = 0;                          // 如果是首次入队，此时的头下标能不再为默认值-1，而为0
        }
        if (this.tail == this.size - 1) {           //如果尾下标等于数组大小size - 1，队列可用空间已满
            throw new Exception("队列已满，无法再入队！");
        }
        this.element[++this.tail] = (Object) t;     //尾下标位置的下一个位置插入新元素，同时尾下标+1
        this.length++;                              //长度更新
    }

    /**
     * 出队，从队头出队
     *
     * @return
     */
    public T deQueue() throws Exception {
        if (this.head == this.tail && this.head == -1) {
            throw new Exception("队列已空，无法再出队");
        }
        Object temp = this.element[this.head];// 获取出队数据，等待返回
        this.length--;
        this.head++; // 队头代表数组头，队头+1，代表一个元素从队头出列
        return (T) temp;
    }

    /**
     * 重写toString方法，方便打印
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("(");
        for (int i = this.head; i <= this.tail; i++) {
            buffer.append(this.element[i].toString());
            if (i != this.tail) {
                buffer.append(",");
            }
        }
        buffer.append(")");

        return buffer.toString();
    }
}
