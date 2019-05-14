package fanda.zeng.queue;

import java.util.Random;

public class QueueMain {

    public static void main(String[] args) {
        int opCount = 1000000;
//        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
//        System.out.println("arrayQueue need time = " + testQueue(arrayQueue, opCount));
        System.out.println("loopQueue need time = " + testQueue(loopQueue, opCount));
        System.out.println("linkedListQueue need time = " + testQueue(linkedListQueue, opCount));

    }

    /**
     * 测试队列的运行时间
     */
    private static double testQueue(Queue<Integer> queue, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        // 纳秒转成秒
        return (endTime - startTime) / 1000000000.0;
    }
}
