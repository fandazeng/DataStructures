package fanda.zeng.bst;

import java.util.*;

/**
 * @Description: 二分搜索树
 * @Author: fanda
 * @Date: 2019/5/14
 */
public class BST<E extends Comparable<E>> {

    private Node root;
    private int size;

    /**
     * 向二分搜索树中添加新的元素e
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以 node 为根的二分搜索树中插入元素e，递归算法，
     * 返回插入新节点后二分搜索树的根
     */
    private Node add(Node node, E e) {
        // 递归到底的时候
        if (node == null) {
            // 维护数量
            size++;
            return new Node(e);
        }

        // 插入的元素小于当前节点
        if (e.compareTo(node.e) < 0) {
            // 插入到左子树中
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            // 插入到右子树中
            node.right = add(node.right, e);
        }
        return node;
    }

    /**
     * 查询树中是否包含当前元素e
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 向以 node 为根的树中查询元素e,递归算法
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        } else {
            return true;
        }
    }

    /**
     * 寻找最小元素
     */
    public E minimum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("BST is empty.");
        }
        return minimum(root).e;
    }

    /**
     * 向以 node 为根的树中找最小元素并返回
     */
    private Node minimum(Node node) {
        // 只向左节点查找，找到最左边的节点
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 寻找最大元素
     */
    public E maximum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("BST is empty.");
        }
        return maximum(root).e;
    }

    /**
     * 向以 node 为根的树中寻找最大元素并返回
     */
    private Node maximum(Node node) {
        // 只向右节点查找，找到最右边的节点
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 删除最小元素
     */
    public E removeMin() {
        E min = minimum();
        // 将 root 指向新的根节点
        root = removeMin(root);
        return min;
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
     * 删除最大元素
     */
    public E removeMax() {
        E max = maximum();
        // 将 root 指向新的根节点
        root = removeMax(root);
        return max;
    }

    /**
     * 向以 node 为根的树中删除最大元素
     * 返回删除节点后的树的根
     */
    private Node removeMax(Node node) {
        if (node.right == null) {
            // 找到该节点的左节点(不用管是否为null)
            Node retNode = node.left;
            // 将要删除的节点的左节点置为 null
            node.left = null;
            size--;
            return retNode;
        }

        node.right = removeMax(node.right);
        return node;

    }

    /**
     * 删除任意元素e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 向以 node 为根的树中删除元素e,递归算法
     * 返回删除节点后的树的根
     */
    private Node remove(Node node, E e) {

        if (node == null)
            return null;

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {   // e.compareTo(node.e) == 0

            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点，即删除节点的   后继
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点，即删除节点的   前驱
            // 用这个节点顶替待删除节点的位置
//            Node precursor = maximum(node.left);
//            precursor.left = removeMax(node.left);
//            precursor.right = node.right;

            node.left = node.right = null;

            return successor;
        }
    }

    /**
     * 前序遍历，深度优先遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 前序遍历以 node 为根的树，递归算法
     */
    private void preOrder(Node node) {
        if (node != null) {
            System.out.println("value = " + node.e);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 前序遍历非递归方式实现
     */
    public void preOrderNR() {
        if (isEmpty()) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        // 先将根节点入栈
        stack.push(root);

        // 如果栈内有元素，则循环遍历元素
        while (!stack.isEmpty()) {
            // 处理完栈顶元素后，将元素出栈
            Node curNode = stack.pop();
            System.out.println("value = " + curNode.e);

            // 因为栈是后进先出的，所以先 把当前节点的右节点入栈
            if (curNode.right != null) {
                stack.push(curNode.right);
            }
            // 左节点入栈
            if (curNode.left != null) {
                stack.push(curNode.left);
            }
        }
    }

    /**
     * 层序遍历，一行一行遍历，广度优先遍历
     */
    public void levelOrder() {
        if (isEmpty()) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        // 先将根节点入队
        queue.add(root);

        // 如果栈队有元素，则循环遍历元素
        while (!queue.isEmpty()) {
            Node curNode = queue.remove();
            System.out.println("value = " + curNode.e);

            // 因为队列是先进先出的，所以先把当前节点的左节点入队
            if (curNode.left != null) {
                queue.add(curNode.left);
            }
            // 右节点入队
            if (curNode.right != null) {
                queue.add(curNode.right);
            }
        }
    }

    /**
     * 中序遍历，节果为从小到大排序的数，深度优先遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 中序遍历以 node 为根的树，递归算法
     */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println("value = " + node.e);
            inOrder(node.right);
        }
    }

    /**
     * 后序遍历，深度优先遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 后序遍历以 node 为根的树，递归算法
     */
    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println("value = " + node.e);
        }
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
        generateBSTString(root, 0, res);
        return res.toString();
    }

    /**
     * 生成以 node 为根节点，深度为 depth 的描述二叉树的字符串
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateBSTString(depth) + "null\n");
        } else {
            res.append(generateBSTString(depth) + node.e + "\n");
            generateBSTString(node.left, depth + 1, res);
            generateBSTString(node.right, depth + 1, res);
        }
    }

    private String generateBSTString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
        }
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = new int[]{28, 16, 30, 13, 22, 29, 42};
//        int[] nums = new int[]{5,3,6,2,4,8};
//        for (int num : nums) {
//            bst.add(num);
//        }

//        System.out.println("bst.contains(43) = " + bst.contains(43));
//        bst.preOrder();
//        bst.preOrderNR();
//        System.out.println();
//        bst.levelOrder();
//        bst.inOrder();
//        bst.postOrder();
//        System.out.println(bst);
//        bst.removeMin();
//        System.out.println(bst.removeMax());
//        bst.inOrder();
//        System.out.println(bst.mininum());
//        bst.remove(13);
//        System.out.println(bst);

        ArrayList<Integer> numsList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            bst.add(random.nextInt(100));
        }
        while (!bst.isEmpty()) {
            numsList.add(bst.removeMin());
        }
        System.out.println(numsList);

        for (int i = 1; i < numsList.size(); i++) {
            if (numsList.get(i - 1) > numsList.get(i)) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Complete");
    }

}
