package fanda.zeng.queue;

/**
 * @Description: 基于链表实现的队列节构
 * @Author: fanda
 * @Date: 2019/5/13
 */
public class LinkedListQueue<E> implements Queue<E> {

    //头节点和尾节点
    private Node head, tail;

    private int size;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        // 如果链表为空，因为有元素的时 tail 不为 null
        if (tail == null) {
            tail = new Node(e);
            // 此时都指向同一个节点
            head = tail;
        } else {
            // 直接将最后的节点指向新的节点即可
            tail.next = new Node(e);
            // 维护一下 tail 的位置，保证 tail 指向最后的节点
            tail = tail.next;
        }
        // 维护一下数量
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        Node retNode = head;
        // 将当前的头节点指向下一个节点
        head = head.next;
        // 将要删除的节点的下一个节点指向 null ,利于GC释放内存
        retNode.next = null;
        // 如果链表为空，需要将 tail 置空
        if (head == null) {
            tail = null;
        }
        // 维护一下数量
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");
        Node curNode = head;
        while (curNode != null) {
            res.append(curNode + "->");
            curNode = curNode.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    /**
     * @Description: 节点类
     * @Author: fanda
     * @Date: 2019/5/13
     */
    private class Node {
        public E e;
        public Node next;

        public Node() {
        }

        public Node(E e) {
            this.e = e;
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }

    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue.toString());
            if (i % 3 == 2) {//每隔3个元素，移除队首一个元素
                queue.dequeue();
                System.out.println(queue.toString());
            }
        }
    }
}
