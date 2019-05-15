package fanda.zeng.heap;

import java.util.*;

/**
 * @Description: LeetCode 347号题目(前K个高频元素)
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * @Author: fanda
 * @Date: 2019/5/15
 */
public class Soluction347 {

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 2, 3};
        System.out.println(topKFrequent(arr, 2));
    }

    /**
     * 基于标准库的优先队列来处理，复杂度为 O(nlongK)
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : nums) {
            if (treeMap.containsKey(num)) {
                treeMap.put(num, treeMap.get(num) + 1);
            } else {
                treeMap.put(num, 1);
            }
        }
        // 在构造函数中传入一个比较器(定义优先级)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(treeMap::get));
        for (Integer key : treeMap.keySet()) {
            if (pq.size() < k) {
                // 先入队 K 个元素
                pq.add(key);
            } else {
                // 第 K+1 个元素开始做处理
                if (treeMap.get(key) > treeMap.get(pq.peek())) {
                    // 出队最小频次节点（处于树的最顶的节点）
                    pq.remove();
                    // 将新节点放进来
                    pq.add(key);
                }
            }
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        while (!pq.isEmpty()) {
            linkedList.add(pq.remove());
        }
        return linkedList;
    }

}


