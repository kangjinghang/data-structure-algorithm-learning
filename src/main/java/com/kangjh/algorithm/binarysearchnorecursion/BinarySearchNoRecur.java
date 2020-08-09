package com.kangjh.algorithm.binarysearchnorecursion;

/**
 * 二分查找算法（非递归）
 *
 * @author kangjinghang
 * @version 1.0
 * @since 2019-08-07
 */
public class BinarySearchNoRecur {

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 10);
        System.out.println("index=" + index);
    }

    /**
     * 二分查找的非递归实现
     *
     * @param arr    待查找的数组, arr 是升序排序
     * @param target 需要查找的数
     * @return 返回对应下标，-1 表示没有找到
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            // 说明要继续查找
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                // 需要向左边查找
                right = mid - 1;
            } else {
                // 需要向右边查找
                left = mid + 1;
            }
        }
        return -1;
    }

}
