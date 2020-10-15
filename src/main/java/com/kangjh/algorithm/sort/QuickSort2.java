package com.kangjh.algorithm.sort;

import java.util.Arrays;

/**
 * QuickSort2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-10
 * @since 1.0.0
 */
public class QuickSort2 {


    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (arr == null || arr.length == 0) {
            return;
        }
        if (left > right) {
            return;
        }
        int l = left;
        int r = right;
        int key = arr[left];
        while (l != r) {
            while (arr[r] >= key && l < r) {
                r--;
            }
            while (arr[l] <= key && l < r) {
                l++;
            }
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }
        arr[left] = arr[l];
        arr[l] = key;
        quickSort(arr, left, l - 1);
        quickSort(arr, l + 1, right);
    }
//
//    public static void quickSort(int[] arr, int left, int right) {
//        if (arr == null || arr.length == 0) {
//            return;
//        }
//        if (left > right) {
//            return;
//        }
//        int l = left;
//        int r = right;
//        int key = arr[left];
//        while (l != r) {
//            while (arr[r] >= key && l < r) {
//                r--;
//            }
//            while (arr[l] <= key && l < r) {
//                l++;
//            }
//            if (l < r) {
//                int temp = arr[l];
//                arr[l] = arr[r];
//                arr[r] = temp;
//            }
//        }
//        arr[left] = arr[l];
//        arr[l] = key;
//        quickSort(arr, left, l - 1);
//        quickSort(arr, l + 1, right);
//    }
//    public static void quickSort(int[] arr, int left, int right) {
//        int l = left;
//        int r = right;
//        int pivot = arr[(left + right) / 2];
//        int temp;
//        while (l < r) {
//            while (arr[l] < pivot) {
//                l++;
//            }
//            while (arr[r] > pivot) {
//                r--;
//            }
//            if (l >= r) {
//                break;
//            }
//            temp = arr[l];
//            arr[l] = arr[r];
//            arr[r] = temp;
//            if (arr[l] == pivot) {
//                r--;
//            }
//            if (arr[r] == pivot) {
//                l++;
//            }
//            if (l == r) {
//                r--;
//                l++;
//            }
//            if (l < right) {
//                quickSort(arr, l, right);
//            }
//            if (left < r) {
//                quickSort(arr, left, r);
//            }
//        }
//    }

}
