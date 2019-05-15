package fanda.zeng.heap;

import fanda.zeng.array.Array;

import java.util.Random;

/**
 * @Description: 最大堆
 * @Author: fanda
 * @Date: 2019/5/15
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        // 从最后一个节点的父子节点开始处理，少了一半的处理节点数量
        for (int i = parent(data.getSize() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    /**
     * 向堆中添加元素
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 元素值上浮过程
     */
    private void siftUp(int k) {
        // 不是根节点，而且父节点比孩子节点小
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            // 则交换两者之间的值
            data.swap(k, parent(k));
            // 将当前索引变成父节点索引
            k = parent(k);
        }
    }

    /**
     * 看堆中的最大元素
     */
    public E findMax() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return data.get(0);
    }

    /**
     * 取出堆中最大的元素
     */
    public E extractMax() {
        // 先拿到最大的元素值
        E ret = findMax();

        // 根元素和最后的一个元素交换值
        data.swap(0, data.getSize() - 1);
        // 删除最后一个元素
        data.removeLast();
        // 将元素进行下沉操作
        siftDown(0);

        return ret;
    }

    /**
     * 元素值下沉过程
     */
    private void siftDown(int k) {
        // 如果不是叶子节点，则循环处理
        while (leftChild(k) < data.getSize()) {
            // 在此轮循环中,data[k]和data[j]交换位置
            int j = leftChild(k);  // 此时 j 代表左孩子节点
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                // 如果有右孩子且右孩子比左孩子大，则将索引加1，此时 j 代表右孩子节点
                j++;
            }
            // data[j] 是 leftChild 和 rightChild 中的最大值
            if (data.get(k).compareTo(data.get(j)) > 0) {
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

    /**
     * 取出堆中的最大元素，并且替换成元素e
     */
    public E replace(E e) {
        E ret = findMax();
        // 将堆中最大的元素替换为 e
        data.set(0, e);
        // 下沉操作
        siftDown(0);
        return ret;
    }

    /**
     * 返回堆中的元素个数
     */
    public int size() {
        return data.getSize();
    }

    /**
     * 返回一个布尔值, 表示堆中是否为空
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 does not have parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    public static void main(String[] args) {
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                throw new IllegalArgumentException("Error");
            }
        }

        System.out.println("Test MaxHeap completed.");
    }

}
