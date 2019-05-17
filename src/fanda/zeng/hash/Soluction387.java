package fanda.zeng.hash;

import java.util.TreeMap;

/**
 * @Description: LeetCode 387号题目（字符串中的第一个唯一字符）
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 案例:
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 * @Author: fanda
 * @Date: 2019/5/17 
 */ 
public class Soluction387 {

    public static void main(String[] args) {
        System.out.println(firstUniqChar2("leetcode"));
    }

    /**
     * 返回字符串中的第一个唯一字符的索引，不存在返回 -1
     */
    public static int firstUniqChar(String s) {
        TreeMap<Character, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (treeMap.containsKey(c)) {
                treeMap.put(c, treeMap.get(c) + 1);
            } else {
                treeMap.put(c, 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (treeMap.get(c) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回字符串中的第一个唯一字符的索引，不存在返回 -1
     */
    public static int firstUniqChar2(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
