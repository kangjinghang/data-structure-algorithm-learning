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
            int min = arr[minIndex];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        System.out.println("arr: " + Arrays.toString(arr));
    }

}
