package fanda.zeng.stack;

/**
 * @Description: 定义栈的接口
 * @Author: fanda
 * @Date: 2019/4/23
 */
public interface Stack<E> {

    /**
     * 返回栈内元素的个数
     */
    int getSize();

    /**
     * 栈内元素个数是否为空
     */
    boolean isEmpty();

    /**
     * 向栈顶添加一个元素
     */
    void push(E e);

    /**
     * 移除栈顶元素
     */
    E pop();

    /**
     * 查看栈顶元素
     */
    E peek();
}
