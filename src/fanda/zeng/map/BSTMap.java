package fanda.zeng.map;

/**
 * @Description: 基于二分搜索树实现的映射类
 * @Author: fanda
 * @Date: 2019/5/15 
 */ 
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node root;
    private int size;

    // 向二分搜索树中添加新的元素(key, value)
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 向以node为根的二分搜索树中插入元素(key, value)，递归算法
     * 返回插入新节点后二分搜索树的根
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            // 不存在，添加一个节点
            return new Node(key, value);
        }
        if (node.key.compareTo(key) < 0) {
            node.right = add(node.right, key, value);
        } else if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, key, value);
        } else {
            // 存在，更新值
            node.value = value;
        }

        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node == null) {
            // 节点不存在，返回 null
            return null;
        } else {
            root = remove(root, key);
            return node.value;
        }
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node successor = minimum(node.right);
            successor.left = node.left;
            successor.right = removeMin(node.right);
            node.left = node.right = null;

            return successor;

        }
        return node;
    }

    /**
     * 向以 node 为根的树中删除最小元素
     * 返回删除节点后的树的根
     */
    private Node removeMin(Node node) {
        // 找到要删除的节点
        if (node.left == null) {
            // 找到该节点的右节点(不用管是否为null)
            Node retNode = node.right;
            // 将要删除的节点的右节点置为 null
            node.right = null;
            // 维护数量
            size--;
            // 返回右节点
            return retNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 向以 node 为根的树中找最小元素并返回
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 向以 node 为根的树中查询 key ,递归算法
     */
    private boolean contains(Node node, K key) {
        if (node == null) {
            return false;
        }

        if (key.compareTo(node.key) < 0) {
            return contains(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return contains(node.right, key);
        } else {
            return true;
        }
    }

    @Override
    public boolean contains(K key) {
//        if (key == null) {
//            return false;
//        } else {
//            return contains(root, key);
//        }

        return getNode(root, key) != null;

    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    /**
     * 向以 node 为根的树中查询 key 对应的节点,递归算法
     */
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            // 不存在该键值对，无法修改，抛出异常
            throw new IllegalArgumentException(key + " does not exist!");
        } else {
            // 存在键值对，直接更新值
            node.value = newValue;
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

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
