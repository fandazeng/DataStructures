package fanda.zeng.map;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @Description: 350. 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * @Author: fanda
 * @Date: 2019/5/6 
 */ 
public class Solution350 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 2, 1};
        int[] nums2 = new int[]{2,2};

        int[] result = intersect(nums1, nums2);

        for (int i : result) {
            System.out.println(i);
        }
    }

    /**
     * 求两个数组的交集，包含重复元素
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        // 将第一个数组的数据放到 Map 中
        for (int i : nums1) {
            if (treeMap.containsKey(i)) {
                // 存在，数量加1
                treeMap.put(i, treeMap.get(i) + 1);
            } else {
                // 不存在，数量设为1
                treeMap.put(i, 1);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (treeMap.containsKey(i)) {
                list.add(i);
                // 如果包含了元素，移除元素
                treeMap.put(i, treeMap.get(i) - 1);
                // 元素的数量为0，移除键值对
                if (treeMap.get(i) == 0) {
                    treeMap.remove(i);
                }
            }
        }

        // 把交集数据放到数组里面并返回
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

}
