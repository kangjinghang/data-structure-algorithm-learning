package com.kangjh.algorithm.sort;

import java.util.Arrays;

/**
 * 鸡尾酒排序
 *
 * @author <a href="kangjh@shukun.net">kangjinghang</a>
 * @date 2021-02-19
 * @since 1.0.0
 */
public class CocktailSort {

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2, 5, 12, 55, 99, 0};
        int temp;
        for (int i = 0; i < arr.length / 2; i++) {
            boolean isSorted = true;
            for (int j = i; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }

            isSorted = true;
            for (int j = arr.length - i - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }


        System.out.println(Arrays.toString(arr));
    }

}
