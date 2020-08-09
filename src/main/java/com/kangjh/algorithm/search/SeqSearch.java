package com.kangjh.algorithm.search;

/**
 * 顺序查找
 *
 * @author kangjinghang
 * @version 1.0
 * @since 2019-07-19
 */
public class SeqSearch {

    public static void main(String[] args) {
        // 没有顺序的数组
        int arr[] = {1, 9, 11, -1, 34, 89};
        int index = seqSearch(arr, 89);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到，下标为=" + index);
        }
    }

    /**
     * 这里我们实现的线性查找是找到一个满足条件的值，就返回
     */
    public static int seqSearch(int[] arr, int value) {
        // 线性查找是逐一比对，发现有相同值，就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

}
