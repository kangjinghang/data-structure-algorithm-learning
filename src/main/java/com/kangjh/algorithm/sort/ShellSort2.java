package com.kangjh.algorithm.sort;

import java.util.Arrays;

/**
 * ShellSort2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-10
 * @since 1.0.0
 */
public class ShellSort2 {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i += gap) {
                int insertIndex = i - gap;
                int insertValue = arr[i];
                while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                    arr[insertIndex + gap] = arr[insertIndex];
                    insertIndex -= gap;
                }
                arr[insertIndex + gap] = insertValue;
            }
        }
        System.out.println("arr: " + Arrays.toString(arr));
    }

}



