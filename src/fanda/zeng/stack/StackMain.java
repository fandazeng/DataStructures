package fanda.zeng.stack;

import java.util.Random;

public class StackMain {

    public static void main(String[] args) {
        int opCount = 100000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        System.out.println("arrayStack need time = " + testStack(arrayStack, opCount));
        System.out.println("linkedListStack need time = " + testStack(linkedListStack, opCount));
    }

    /**
     * 测试栈的运行时间
     */
    private static double testStack(Stack<Integer> stack, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
