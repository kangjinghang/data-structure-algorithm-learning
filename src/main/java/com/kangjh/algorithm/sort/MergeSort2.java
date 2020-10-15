package com.kangjh.algorithm.sort;

import java.util.Arrays;

/**
 * MergeSort2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-10
 * @since 1.0.0
 */
public class MergeSort2 {

    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        //归并排序需要一个额外空间
        int temp[] = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("归并排序后=" + Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, right, temp);
        }
    }

    public static void merge(int[] arr, int left, int right, int[] temp) {
        int mid = (left + right) / 2;
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

}
