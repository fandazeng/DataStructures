package fanda.zeng.set;

import fanda.zeng.linkedlist.LinkedList;
import fanda.zeng.utils.FileOperation;

import java.util.ArrayList;

/**
 * @Description: 基于LinkedList实现的集合
 * @Author: fanda
 * @Date: 2019/5/14
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> linkedList ;

    public LinkedListSet() {
        linkedList = new LinkedList<>();
    }

    @Override
    public void add(E e) {  // 时间复杂度为 O(n)
        // 如果不包含元素，才添加
        if (!contains(e)) {
            linkedList.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {   // 时间复杂度为 O(n)
        linkedList.removeElement(e);
    }

    @Override
    public boolean contains(E e) {  // 时间复杂度为 O(n)
        return linkedList.contains(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        boolean isSuccesss = FileOperation.readFile("F:\\java_projects\\data_structure\\DataStructures\\src\\a-tale-of-two-cities.txt", words);
        if (isSuccesss) {
            LinkedListSet<String> set = new LinkedListSet<>();
            System.out.println("A-Tale-Of-Two-Cities : " + words.size());
            for (String s : words) {
                set.add(s);
            }
            System.out.println("A-Tale-Of-Two-Cities Total different words : " + set.getSize());
        }
    }
}
