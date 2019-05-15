package fanda.zeng.map;

/**
 * @Description: 基于链表实现的映射类
 * @Author: fanda
 * @Date: 2019/5/15
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    // 虚拟头节点
    private Node dummyHead;

    private int size;

    public LinkedListMap() {
        dummyHead = new Node(null, null);
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        // 不存在该键值对
        if (node == null) {
            // 在链表头添加一个节点
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {
            // 存在键值对，直接更新值
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node prevNode = dummyHead;
        while (prevNode.next != null) {
            Node delNode = prevNode.next;
            if (delNode.key.equals(key)) {
                prevNode.next = delNode.next;
                delNode.next = null;
                size--;
                return delNode.value;
            } else {
                prevNode = prevNode.next;
            }
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node curNode = getNode(key);
        return curNode == null ? null : curNode.value;
    }

    /**
     * 获取当前 KEY 对应的 Node
     */
    private Node getNode(K key) {
        Node curNode = dummyHead.next;
        while (curNode != null) {
            if (curNode.key.equals(key)) {
                return curNode;
            }
            curNode = curNode.next;
        }
        return null;
    }

    @Override
    public void set(K key, V newValue) {
        Node curNode = getNode(key);
        if (curNode == null) {
            // 不存在该键值对，无法修改，抛出异常
            throw new IllegalArgumentException(key + " does not exist!");
        } else {
            // 存在键值对，直接更新值
            curNode.value = newValue;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedListMap: ");

        for (Node node = dummyHead.next; node != null; node = node.next) {
            res.append(node + "->");
        }

        res.append("NULL");
        return res.toString();
    }

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }

    }
}
