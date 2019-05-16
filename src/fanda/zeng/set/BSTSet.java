package fanda.zeng.set;

import fanda.zeng.tree.BST;
import fanda.zeng.utils.FileOperation;

import java.util.ArrayList;

/**
 * @Description: 基于BST实现的集合
 * @Author: fanda
 * @Date: 2019/5/14
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet() {
        this.bst = new BST<>();
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public void add(E e) {
        // bst 本身支持不添加重复的元素
        bst.add(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        boolean isSuccesss = FileOperation.readFile("F:\\java_projects\\data_structure\\DataStructures\\src\\a-tale-of-two-cities.txt", words);
        if (isSuccesss) {
            BST<String> set = new BST<>();
            System.out.println("A-Tale-Of-Two-Cities : " + words.size());
            for (String s : words) {
                set.add(s);
            }
            System.out.println("A-Tale-Of-Two-Cities Total different words : " + set.getSize());
        }
    }
}
