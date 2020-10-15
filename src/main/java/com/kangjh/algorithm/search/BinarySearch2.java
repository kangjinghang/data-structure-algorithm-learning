package com.kangjh.algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * BinarySearch2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-11
 * @since 1.0.0
 */
public class BinarySearch2 {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000, 1000, 1234};
        int resIndex = binarySearch(arr, 0, arr.length - 1, 1234);
        System.out.println("resIndex=" + resIndex);
        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndex=" + list);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        if (left > right || arr[left] > findVal || arr[right] < findVal) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findVal > midValue) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midValue) {
            return binarySearch(arr, left, mid, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 思考题： {1,8, 10, 89, 1000, 1000，1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的1000
     * <p>
     * 思路分析
     * 1. 在找到mid 索引值，不要马上返回
     * 2. 向mid 索引值的左边扫描，将所有满足1000， 的元素的下标，加入到集合ArrayList
     * 3. 向mid 索引值的右边扫描，将所有满足1000， 的元素的下标，加入到集合ArrayList
     * 4. 将Arraylist 返回
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        if (left > right || arr[left] > findVal || arr[right] < findVal) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (findVal > midValue) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midValue) {
            return binarySearch2(arr, left, mid, findVal);
        } else {
            ArrayList<Integer> result = new ArrayList<>();
            int index = mid - 1;
            while (index >= 0 && arr[index] == findVal) {
                result.add(index--);
            }
            result.add(mid);
            index = mid + 1;
            while (index <= arr.length - 1 && arr[index] == findVal) {
                result.add(index++);
            }
            return result;
        }
    }

}
