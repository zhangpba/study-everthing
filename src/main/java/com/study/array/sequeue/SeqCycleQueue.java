package com.study.array.sequeue;

/**
 * 循环顺讯队列
 *
 * @author zhangpba
 * @date 2021-09-05
 */
public class SeqCycleQueue<T> implements QueueApi<T> {

    private Object[] element;   // 存放数据的数组
    private int head;   // 头下标，存放队头在数组的位置
    private int tail;   // 尾下标，存放队尾在数组的位置
    private int length; // 队列长度
    private int size;   // 队列可以使用的最大容量

    /**
     * 循环顺序队列的构造函数
     *
     * @param size
     */
    public SeqCycleQueue(int size) {
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
        if (this.tail == this.size) {           //如果尾下标等于数组大小size - 1，队列可用空间已满
            throw new Exception("队列已满，无法再入队！");
        }
        this.tail = ++this.tail % this.size;        // 为了循环，tail%size就是为了循环，回到数组头部
        this.element[this.tail] = (Object) t;     //尾下标位置的下一个位置插入新元素，同时尾下标+1
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
        Object temp = this.element[this.head];      // 获取出队数据，等待返回
        this.length--;
        this.head = ++this.head % this.size;        //移动头下标，有了mod计算，就可以回到数组头部，因为head下标也会回到循环的
        return (T) temp;
    }

    /**
     * 重写toString方法
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("{");
        if (isEmpty()) {
            return "()";
        } else if (this.tail < this.head) { //当队列的队尾循环到数组的前面
            for (int i = this.head; i <= this.size - (this.head + 1) + (this.tail + 1) + this.head; i++) {
                buffer.append(this.element[i % this.size].toString());
                if (i % this.size != this.tail) {
                    buffer.append(",");
                }
            }
            buffer.append("}");
        } else { //正常情况下，内存还没有分配到垃圾数据区
            for (int i = this.head; i <= this.tail; i++) {
                buffer.append(this.element[i].toString());
                if (i != this.tail) {
                    buffer.append(",");
                }
            }
            buffer.append("}");
        }
        return buffer.toString();
    }
}
