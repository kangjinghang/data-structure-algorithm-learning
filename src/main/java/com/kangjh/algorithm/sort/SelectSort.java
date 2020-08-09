package com.kangjh.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author kangjinghang
 * @version 1.0
 * @since 2019-07-17
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                // 说明假定的最小值，并不是最小
                if (min > arr[j]) {
                    // 重置min
                    min = arr[j];
                    // 重置minIndex
                    minIndex = j;
                }
            }
            // 将最小值，放在arr[i], 即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            System.out.println("第" + (i + 1) + "轮后~~");
            System.out.println(Arrays.toString(arr));
        }
    }

}
