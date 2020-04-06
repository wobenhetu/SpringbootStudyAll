package com.springboot.java.huawei;

import java.util.Scanner;

public class Test4 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int[] nums = new int[1001];
            int sum = scanner.nextInt();
            for (int i = 0; i < sum; i++) {
                int n = scanner.nextInt();
                nums[n] = 1;
            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    System.out.println(i);
                }
            }
        }
    }
}
