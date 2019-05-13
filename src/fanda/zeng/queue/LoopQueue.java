package fanda.zeng.queue;

/**
 * @Description: 循环队列(时间复杂度为O (1))，弥补ArrayQueue出队时间复杂度为O(n)的情况
 * @Author: fanda
 * @Date: 2019/5/13
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    // 指向队首的位置
    private int front;
    // 指向队尾即将被添加元素的位置（不是队尾的位置，队尾的下一个位置）
    private int tail;
    // 队内实际存储元素的个数
    private int size;

    public LoopQueue(int capacity) {
        // 为防止元素装满时 front == tail ，所以故意空出一个位置
        // 但为了保证元素个数，把容量做 +1 处理
        data = (E[]) new Object[capacity + 1];
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        // 创建的时候做了 +1 处理，这里应当 -1
        return data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        // 当元素装满时，扩容
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        // 维护 tail 的位置，要循环处理，所以取模运算
        tail = (tail + 1) % data.length;
        size++;
    }

    /**
     * 扩容，均摊时间复杂度O(1)
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            // 从 front 开始，把所有元素复制到新对象中，索引从 0 开始
            newData[i] = data[(i + front) % data.length];
        }
        // 将当前数组对象指向新的数组对象
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        E ret = data[front];
        data[front] = null;// 把没用到的对象置null，利于GC释放内存
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() { return front == tail; }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue: size = %d , capacity = %d\n", getSize(), getCapacity()));
        res.append("front [");
        // 这种遍历方式也是可以的
       /* for (int i = 0; i < size; i++) {
            res.append(data[(i+front)%data.length]);
            if (i != size - 1) {
                res.append(", ");
            }
        }*/

        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            loopQueue.enqueue(i);
            System.out.println(loopQueue.toString());
            if (i % 3 == 2) {//每隔3个元素，移除队首一个元素
                loopQueue.dequeue();
                System.out.println(loopQueue.toString());
            }
        }
    }
}
