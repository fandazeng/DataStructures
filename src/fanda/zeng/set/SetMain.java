package fanda.zeng.set;

import fanda.zeng.utils.FileOperation;

import java.util.ArrayList;

public class SetMain {

    public static void main(String[] args) {
        String fileName = "F:\\java_projects\\data_structure\\DataStructures\\src\\a-tale-of-two-cities.txt";
        Set<String> bstSet = new BSTSet<>();
        Set<String> linkedListSet = new LinkedListSet<>();
        System.out.println("testSet(bstSet,fileName) = " + testSet(bstSet, fileName));
        System.out.println("testSet(linkedListSet,fileName) = " + testSet(linkedListSet, fileName));
    }

    /**
     * 测试集合的运行时间
     */
    private static double testSet(Set<String> set, String fileName) {
        long startTime = System.nanoTime();
        ArrayList<String> words = new ArrayList<>();
        boolean isSuccesss = FileOperation.readFile("F:\\java_projects\\data_structure\\DataStructures\\src\\a-tale-of-two-cities.txt", words);
        if (isSuccesss) {
            for (String s : words) {
                set.add(s);
            }
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
