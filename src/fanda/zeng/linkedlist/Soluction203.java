package fanda.zeng.linkedlist;

/**
 * @Description: LeetCode 203号题目（移除链表元素）
 * @Author: fanda
 * @Date: 2019/5/13
 */
public class Soluction203 {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 53, 2, 7, 12, 5, 3};
        System.out.println(removeElements3(new ListNode(arr), 3));
    }

    /**
     * 删除链表中等于给定值 val 的所有节点，不带虚拟头节点的处理方式
     */
    public ListNode removeElements(ListNode head, int val) {
        // 如果删除的正好是头节点，这里用 while 语句，因为下个节点也可能要删除掉
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        // 链表为空了，直接返回 null
        if (head == null) {
            return null;
        }

        // 删除的值在中间位置时
        ListNode prevNode = head;
        while (prevNode.next != null) {
            if (prevNode.next.val == val) {
                ListNode delNode = prevNode.next;
                prevNode.next = delNode.next;
                delNode.next = null;
            } else {
                prevNode = prevNode.next;
            }
        }
        return head;
    }

    /**
     * 删除链表中等于给定值 val 的所有节点，带虚拟头节点的处理方式
     */
    public static ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1, head);
        ListNode prevNode = dummyHead;
        while (prevNode.next != null) {
            if (prevNode.next.val == val) {
                // 如果 prevNode.next 是要删除的值，则跳过，直接指向它的下一个节点
                prevNode.next = prevNode.next.next;
            } else {
                prevNode = prevNode.next;
            }
        }
        // 返回真正的头结点
        return dummyHead.next;
    }

    /**
     * 删除链表中等于给定值 val 的所有节点，递归方式
     */
    public static ListNode removeElements3(ListNode node, int val) {
        // 递归到底的情况，即最基本的问题
        if (node == null) {
            return null;
        }
        // 把原问题转化成更小的问题
        node.next = removeElements3(node.next, val);
        return node.val == val ? node.next : node;
    }
}
