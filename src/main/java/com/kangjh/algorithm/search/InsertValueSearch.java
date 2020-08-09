package com.kangjh.algorithm.search;

/**
 * @author kangjinghang
 * @version 1.0
 * @since 2019-07-19
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1234};
        int index = insertValueSearch(arr, 0, arr.length - 1, 89);
        System.out.println("index = " + index);
    }

    /**
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 如果找到就返回下标，如果没有找到，就返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        // 注意：findVal < arr[0] 和findVal > arr[arr.length - 1] 必须需要
        // 否则我们得到的mid 可能越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        // 求出mid, 自适应
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        // 说明应该向右边递归
        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
            // 说明向左递归查找
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

}
