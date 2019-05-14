package fanda.zeng.set;

/** 
 * @Description: 定义集合的接口
 * @Author: fanda
 * @Date: 2019/5/14 
 */ 
public interface Set<E> {
    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();
}
