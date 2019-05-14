package fanda.zeng.linkedlist;

/** 
 * @Description: 求和，用于展示递归方法
 * @Author: fanda
 * @Date: 2019/5/14 
 */ 
public class Sum {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("sum() = " + sum(arr));
    }

    /**
     * 对数组 arr 进行求和
     */
    public static int sum(int[] arr) {
        if (arr == null) {
            return 0;
        }
        return sum(arr, 0);
    }

    /**
     *  计算 arr[index...n]这个区间内所有数字的和
     */
    private static int sum(int[] arr, int index) {
        // 递归到底的情况，即最基本的问题
        if (index == arr.length) {
            return 0;
        }
        // 把原问题转化成更小的问题
        return arr[index] + sum(arr, index + 1);
    }
}
