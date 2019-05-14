package fanda.zeng.linkedlist;

/**
 * @Description: 链表实现类
 * @Author: fanda
 * @Date: 2019/5/13
 */
public class LinkedList<E> {

    // 虚拟头节点
    private Node dummyHead;

    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
    }

    /**
     * 向链表的 index 索引位置添加新的元素，时间复杂度O(n)
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add Failed.Illegal index.");
        }
        // 默认第一个节点为虚拟头节点
        Node prevNode = dummyHead;
        // 遍历 index -1 次，找到 index 的前一个节点
        for (int i = 0; i < index; i++) {
            prevNode = prevNode.next;
        }
        // 将前一个节点的下一个节点指向新建的节点
        // 新建节点的下一个节点指向前一个节点的下一个节点
        prevNode.next = new Node(e, prevNode.next);
        size++;
    }

    /**
     * 在链表头添加新的元素，时间复杂度O(1)
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在链表尾添加新的元素，时间复杂度O(n)
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 删除指定 index 索引位置的节点，时间复杂度O(n)
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove Failed.Illegal index.");
        }

        Node prevNode = dummyHead;
        // 找到前一个节点
        for (int i = 0; i < index; i++) {
            prevNode = prevNode.next;
        }
        // 要删除的节点
        Node delNode = prevNode.next;
        prevNode.next = delNode.next;
        // 将要删除的节点的下一个节点指向 null ,利于GC释放内存
        delNode.next = null;
        size--;
        return delNode.e;
    }

    /**
     * 删除元素
     */
    public void removeElement(E e) {
        dummyHead.next = removeElement(dummyHead.next, e);
    }

    /**
     * 向以 node 为根节点的链表中删除元素为e的节点
     * 返回删除元素后的根节点
     */
    private Node removeElement(Node node, E e) {
        if (node == null) {
            return node;
        }
        if (node.e.equals(e)) {
            size--;
            return node.next;
        } else {
            node.next = removeElement(node.next, e);
        }
        return node;
    }

    /**
     * 删除元素非递归方式
     */
    public void removeElementNR(E e) {
        Node prevNode = dummyHead;
        for (int i = 0; i < size; i++) {
            Node curNode = prevNode.next;
            if (curNode.e.equals(e)) {
                prevNode.next = curNode.next;
                curNode.next = null;
                size--;
                break;
            } else {
                prevNode = prevNode.next;
            }
        }
    }

    /**
     * 删除链表头的节点，时间复杂度O(1)
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除链表尾的节点，时间复杂度O(n)
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 更新 index 索引为新的元素，时间复杂度O(n)
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set Failed.Illegal index.");
        }

        Node curNode = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        curNode.e = e;
    }

    /**
     * 获取链表的 index 索引位置的元素，时间复杂度O(n)
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get Failed.Illegal index.");
        }
        Node curNode = dummyHead.next;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        return curNode.e;
    }

    /**
     * 获取链表头的元素
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取链表尾的元素
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 查找链表中是否存在元素e，时间复杂度O(n)
     */
    public boolean contains(E e) {
        Node curNode = dummyHead.next;
        while (curNode != null) {
            if (curNode.e.equals(e)) {
                return true;
            } else {
                curNode = curNode.next;
            }
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedList: ");
        // 这种写法是可以的
       /* Node curNode = dummyHead.next;
        while (curNode != null) {
            res.append(curNode + "->");
            curNode = curNode.next;
        }*/

        for (Node node = dummyHead.next; node != null; node = node.next) {
            res.append(node + "->");
        }

        res.append("NULL");
        return res.toString();
    }

    /**
     * @Description: 节点类
     * @Author: fanda
     * @Date: 2019/4/23
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
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 1; i++) {
            linkedList.addFirst(i);
        }
        linkedList.add(2,100);
        System.out.println(linkedList);
        linkedList.remove(2);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);

        linkedList.removeElementNR(8);
        System.out.println(linkedList);
    }

}
