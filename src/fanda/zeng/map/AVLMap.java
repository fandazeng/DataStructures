package fanda.zeng.map;

import fanda.zeng.tree.AVLTree;

/** 
 * @Description: 基于 AVL 树实现的映射
 * @Author: fanda
 * @Date: 2019/5/16 
 */ 
public class AVLMap<K extends Comparable<K>,V> implements Map<K,V> {

    private AVLTree<K, V> tree;

    public AVLMap() {
        tree = new AVLTree<>();
    }

    @Override
    public void add(K key, V value) {
        tree.add(key,value);
    }

    @Override
    public V remove(K key) {
        return tree.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return tree.contains(key);
    }

    @Override
    public V get(K key) {
        return tree.get(key);
    }

    @Override
    public void set(K key, V newValue) {
        tree.set(key,newValue);
    }

    @Override
    public int getSize() {
        return tree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

}
