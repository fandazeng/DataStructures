package fanda.zeng.tree;

import fanda.zeng.utils.FileOperation;

import java.util.ArrayList;

/**
 * @Description: 红黑树
 * @Author: fanda
 * @Date: 2019/5/16
 */
public class RBTree<K extends Comparable<K>, V> {

    // true 定义为 红色
    private static final boolean RED = true;
    // false 定义为 黑色
    private static final boolean BLACK = false;

    private Node root;
    private int size;


    /**
     * 右旋转，返回旋转后的根节点
     */
    private Node rightRotate(Node node) {
        Node retNode = node.left;
        node.left = retNode.right;
        retNode.right = node;

        // 新的根节点应该保持原来根节点的颜色值
        retNode.color = node.color;
        // 设为红色，表示加进来的节点跟之前的节点融合
        node.color = RED;
        return retNode;
    }

    /**
     * 左旋转，返回旋转后的根节点
     */
    private Node leftRotate(Node node) {
        Node retNode = node.right;
        node.right = retNode.left;
        retNode.left = node;

        // 新的根节点应该保持原来根节点的颜色值
        retNode.color = node.color;
        // 设为红色，表示加进来的节点跟之前的节点融合
        node.color = RED;
        return retNode;
    }

    /**
     * 颜色翻转
     */
    private void flipColors(Node node) {
        // 将左右孩子变成黑色
        node.left.color =BLACK ;
        node.right.color = BLACK;
        // 根节点因为要向上融合，要设为红色
        node.color = RED;
    }

    /**
     * 判断节点的颜色
     */
    private boolean isRed(Node node) {
        // 空节点默认为黑色节点
        if (node == null) {
            return false;
        }
        return node.color;
    }

    /**
     * 向红黑树中添加新的元素(key, value)
     */
    public void add(K key, V value) {
        root = add(root, key, value);
        // 最终根节点为黑色节点
        root.color = BLACK;
    }

    /**
     * 向以node为根的红黑树中插入元素(key, value)，递归算法
     * 返回插入新节点后红黑树的根
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (node.key.compareTo(key) < 0) {
            node.right = add(node.right, key, value);
        } else if (node.key.compareTo(key) > 0) {
            node.left = add(node.left, key, value);
        } else {
            node.value = value;
        }

        // 右节点为红色，左节点黑色，需要 左旋转
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        // 左节点为红色，左节点的左节点还是红色，需要 右旋转
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        // 左右节点均为红色，需要颜色翻转
        if (isRed(node.left) && isRed(node.right)) {
             flipColors(node);
        }

        return node;
    }

    /**
     * 向以 node 为根的树中查询key,递归算法
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

    public boolean contains(K key) {
        if (key == null) {
            return false;
        } else {
            return contains(root, key);
        }
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    /**
     * 向以 node 为根的树中查询key对应的结点,递归算法
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

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " does not exist!");
        } else {
            node.value = newValue;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        // 定义节点的颜色
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            // 默认新节点为红色，因为新节点肯定要和之前的节点做融合
            this.color = RED;
        }
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("F:\\java_projects\\data_structure\\DataStructures\\src\\pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }

}
