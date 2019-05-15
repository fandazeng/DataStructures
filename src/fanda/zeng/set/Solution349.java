package fanda.zeng.set;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @Description: LeetCode 349号题目(两个数组的交集)
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 * <p>
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * @Author: fanda
 * @Date: 2019/5/6
 */
public class Solution349 {

    /**
     * 求两个数组的交集，不包含重复元素
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        // 将第一个数组做去重处理
        for (int i : nums1) {
            treeSet.add(i);
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (int i : nums2) {
            if (treeSet.contains(i)) {
                result.add(i);
                // 如果包含了，移除元素
                treeSet.remove(i);
            }
        }
        // 把交集数据放到数组里面并返回
        int[] array = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            array[i] = result.get(i);
        }
        return array;
    }

}
