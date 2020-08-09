package com.kangjh.algorithm.sort;

import java.util.Arrays;

/**
 * @author kangjinghang
 * @version 1.0
 * @since 2019-07-19
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int count = 0;
        // 增量gap, 并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素开始，逐个对每个组来排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[i];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while 后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
            System.out.println("希尔排序第" + (++count) + "轮=" + Arrays.toString(arr));
        }

    }

}
