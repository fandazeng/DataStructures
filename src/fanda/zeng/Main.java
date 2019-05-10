package fanda.zeng;

import fanda.zeng.array.Array;

public class Main {

    public static void main(String[] args) {


    }

    public static int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }
}
