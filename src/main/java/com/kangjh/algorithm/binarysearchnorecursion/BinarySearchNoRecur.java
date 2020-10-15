package com.kangjh.algorithm.binarysearchnorecursion;

/**
 * 二分查找算法（非递归）
 *
 * @author kangjinghang
 * @version 1.0
 * @since 2019-08-07
 */
public class BinarySearchNoRecur {

    public static void main(String[] args) throws Exception {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch2(arr, 10);
        System.out.println("index=" + index);
        index = binarySearch2(arr, 100);
        System.out.println("index=" + index);
        index = binarySearch2(arr, 1000);
        System.out.println("index=" + index);

        System.out.println();
        index = binarySearch4(arr, 10);
        System.out.println("index=" + index);
        index = binarySearch4(arr, 100);
        System.out.println("index=" + index);
        index = binarySearch4(arr, 1000);
        System.out.println("index=" + index);
    }

    public static int binarySearch4(int[] arr, int target) {
        return binarySearch4(0, arr.length - 1, arr, target);
    }

    public static int binarySearch4(int left, int right, int[] arr, int target) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (target == midValue) {
            return mid;
        } else if (target < midValue) {
            return binarySearch4(left, mid - 1, arr, target);
        } else {
            return binarySearch4(mid + 1, right, arr, target);
        }
    }

    public static int binarySearch2(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int midValue = arr[mid];
            if (target == midValue) {
                return mid;
            } else if (target < midValue) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
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
