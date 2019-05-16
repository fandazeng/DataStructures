package fanda.zeng.avl;

import fanda.zeng.bst.BST;
import fanda.zeng.map.BSTMap;
import fanda.zeng.utils.FileOperation;

import java.util.ArrayList;
import java.util.Collections;

public class AVLMain {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("F:\\java_projects\\data_structure\\DataStructures\\src\\a-tale-of-two-cities.txt", words)) {
            System.out.println("Total words: " + words.size());

            // 排序之后，二分搜索树会退化成链表
//            Collections.sort(words);

            // Test BST
            long startTime = System.nanoTime();

            BSTMap<String, Integer> bst = new BSTMap<>();
            for (String word : words) {
                if (bst.contains(word))
                    bst.set(word, bst.get(word) + 1);
                else
                    bst.add(word, 1);
            }

            for(String word: words)
                bst.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST: " + time + " s");


            // Test AVL Tree
             startTime = System.nanoTime();

            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.set(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }

            for (String word : words)
                avl.contains(word);

             endTime = System.nanoTime();

             time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");
        }

        System.out.println();
    }
}
