package fanda.zeng.queue;

import fanda.zeng.heap.MaxHeap;

/**
 * @Description: 基于最大堆实现的优先队列
 * @Author: fanda
 * @Date: 2019/5/15
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> heap;

    public PriorityQueue() {
        heap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        heap.add(e);
    }

    @Override
    public E dequeue() {
        return heap.extractMax();
    }

    @Override
    public E getFront() {
        return heap.findMax();
    }
}
