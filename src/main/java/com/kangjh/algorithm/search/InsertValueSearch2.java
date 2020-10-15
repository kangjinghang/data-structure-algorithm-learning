package com.kangjh.algorithm.search;

/**
 * InsertValueSearch2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-11
 * @since 1.0.0
 */
public class InsertValueSearch2 {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1234};
        int index = insertValueSearch(arr, 0, arr.length - 1, 189);
        System.out.println("index = " + index);
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        if (left > right || arr[left] > findVal || arr[right] < findVal) {
            return -1;
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (findVal > midValue) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midValue) {
            return insertValueSearch(arr, left, mid, findVal);
        } else {
            return mid;
        }
    }

}
