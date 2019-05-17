package fanda.zeng.map;

import fanda.zeng.utils.FileOperation;

import java.util.ArrayList;

public class MapMain {

    public static void main(String[] args) {
        String fileName = "F:\\java_projects\\data_structure\\Map\\src\\pride-and-prejudice.txt";
        Map<String,Integer> linkedListMap = new LinkedListMap<>();
        BSTMap<String, Integer> bstMap = new BSTMap<>();
//        AVLMap<String, Integer> avlMap = new AVLMap<>();
        System.out.println("linkedListMap need time = " + testMap(linkedListMap, fileName));
        System.out.println("pride show num of times = " + linkedListMap.get("pride"));

        System.out.println();

        System.out.println("bstMap need time = " + testMap(bstMap, fileName));
        System.out.println("pride show num of times = " + bstMap.get("pride"));

        System.out.println();

//        System.out.println("AVLMap need time = " + testMap(avlMap, fileName));
//        System.out.println("pride show num of times = " + avlMap.get("pride"));
    }

    /**
     * 测试映射的运行时间
     */
    private static double testMap(Map<String,Integer> map, String fileName) {
        long startTime = System.nanoTime();
        ArrayList<String> words = new ArrayList<>();
        boolean isSuccesss = FileOperation.readFile(fileName, words);
        if (isSuccesss) {
            System.out.println("Pride and Prejudice Total words : " + words.size());
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word,1);
                }
            }
            System.out.println("Pride and Prejudice Total different words : " + map.getSize());
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
