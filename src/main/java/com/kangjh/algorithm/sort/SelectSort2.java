package com.kangjh.algorithm.sort;

import java.util.Arrays;

/**
 * SelectSort2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-09
 * @since 1.0.0
 */
public class SelectSort2 {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
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
