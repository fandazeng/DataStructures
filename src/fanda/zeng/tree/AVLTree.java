package fanda.zeng.tree;

import fanda.zeng.utils.FileOperation;

import java.util.ArrayList;

/**
 * @Description: AVLTree 自平衡的二叉树
 * @Author: fanda
 * @Date: 2019/5/6
 */
public class AVLTree<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    /**
     * 获取节点node的高度
     */
    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    /**
     * 获取节点对应的平衡因子，即左右子树的高度差
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        } else {
            return getHeight(node.left) - getHeight(node.right);
        }
    }

    /**
     * 判断该二叉树是否是一棵二分搜索树
     */
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        // 如果是一棵二分搜索树，中序遍历后的数据肯定是从小到大排序的
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断该二叉树是否是一棵平衡二叉树
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * 判断以Node为根的二叉树是否是一棵平衡二叉树，递归算法
     */
    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        if (Math.abs(getBalanceFactor(node)) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 右旋转
     */
    private Node rightRorate(Node node) {
        Node retNode = node.left;
        Node x = retNode.right;
        node.left = x;
        retNode.right = node;

        // 更新height，一定要先更新 node 的，后更新 retNode
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        return retNode;
    }

    /**
     * 右旋转
     */
    private Node leftRorate(Node node) {
        Node retNode = node.right;
        Node x = retNode.left;
        node.right = x;
        retNode.left = node;

        // 更新height，一定要先更新 node 的，后更新 retNode
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        return retNode;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 向AVL树中添加新的元素(key, value)
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 向以node为根的AVL树中插入元素(key, value)，递归算法
     * 返回插入新节点后AVL树的根
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

        // 更新 height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // 平衡维护
        if (getBalanceFactor(node) > 1 && getBalanceFactor(node.left) >= 0) {
            //LL情况，需要右旋转
            return rightRorate(node);
        }

        if (getBalanceFactor(node) > 1 && getBalanceFactor(node.left) < 0) {
            //LR情况，需要左旋转，然后右旋转
            node.left = leftRorate(node.left);
            return rightRorate(node);
        }

        if (getBalanceFactor(node) < -1 && getBalanceFactor(node.right) <= 0) {
            //RR情况，需要左旋转
            return leftRorate(node);
        }

        if (getBalanceFactor(node) < -1 && getBalanceFactor(node.right) > 0) {
            //RL情况，需要右旋转，然后左旋转
            node.right = rightRorate(node.right);
            return leftRorate(node);
        }

        return node;
    }

    /**
     * 从AVL树中删除键为key的节点
     */
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node == null) {
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

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                Node successor = minimum(node.right);
                //注意这里，不能用removeMin方法，因为会打破平衡性（也可以在removeMin方法里进行平衡维护）
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        //注意删除的是叶子节点时，retNode可能为null
        if (retNode == null) {
            return null;
        }

        // 更新 height
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        // 平衡维护
        if (getBalanceFactor(retNode) > 1 && getBalanceFactor(retNode.left) >= 0) {
            //LL情况，需要右旋转
            return rightRorate(retNode);
        }

        //疑惑，为什么不能等于0
        if (getBalanceFactor(retNode) > 1 && getBalanceFactor(retNode.left) < 0) {
            //LR情况，需要左旋转，然后右旋转
            node.left = leftRorate(retNode.left);
            return rightRorate(retNode);
        }

        if (getBalanceFactor(retNode) < -1 && getBalanceFactor(retNode.right) <= 0) {
            //RR情况，需要左旋转
            return leftRorate(retNode);
        }

        //疑惑，为什么不能等于0
        if (getBalanceFactor(retNode) < -1 && getBalanceFactor(retNode.right) > 0) {
            //RL情况，需要右旋转，然后左旋转
            retNode.right = rightRorate(retNode.right);
            return leftRorate(retNode);
        }

        return retNode;
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
     * 向以 node 为根的树中寻找最大元素并返回
     */
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
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
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            // 节点高度值默认为1
            height = 1;
        }
    }

    public static void main(String[] args) {

        System.out.println("A-Tale-0f-Two-Cities");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("F:\\java_projects\\data_structure\\DataStructures\\src\\a-tale-of-two-cities.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("AVLTree is bst : " + map.isBST());
            System.out.println("AVLTree is balance : " + map.isBalanced());

            for (String word : words) {
                map.remove(word);
                if (!map.isBalanced() || !map.isBST()) {
                    throw new RuntimeException();
                }
            }

            System.out.println("Completed");
        }

        System.out.println();
    }
}
