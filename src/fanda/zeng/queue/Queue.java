package fanda.zeng.queue;

/**
 * @Description: 定义队列的接口
 * @Author: fanda
 * @Date: 2019/5/13
 */
public interface Queue<E> {

    /**
     * 返回队列内元素的个数
     */
    int getSize();

    /**
     * 队列内元素个数是否为空
     */
    boolean isEmpty();

    /**
     * 向队尾添加一个元素
     */
    void enqueue(E e);

    /**
     * 移除队首元素
     */
    E dequeue();

    /**
     * 查看队首元素
     */
    E getFront();
}
