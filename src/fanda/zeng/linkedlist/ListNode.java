package fanda.zeng.linkedlist;

import java.util.List;

/** 
 * @Description: 列表节点类
 * @Author: fanda
 * @Date: 2019/5/13 
 */ 
public class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * 构造函数，传入数组，创建一个链表，当前的 ListNode 为链表的头节点
     */
    public ListNode(int[] arr) {
        if (arr == null||arr.length == 0) {
            throw new IllegalArgumentException("arr can not be empty");
        }
        // 设置头节点的值
        this.val = arr[0];
        // 当前节点为头节点
        ListNode curNode = this;
        for (int i = 1; i < arr.length; i++) {
            // 头节点指向下一个值的节点
            curNode.next = new ListNode(arr[i]);
            curNode = curNode.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode curNode = this;
        while (curNode != null) {
            res.append(curNode.val).append("->");
            curNode = curNode.next;
        }
        res.append("Null");

        return res.toString();
    }
}
