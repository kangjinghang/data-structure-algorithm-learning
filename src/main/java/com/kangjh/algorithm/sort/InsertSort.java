package com.kangjh.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author kangjinghang
 * @version 1.0
 * @since 2019-07-19
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        int insertValue = 0;
        int insertIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            insertIndex = i - 1;
            insertValue = arr[i];
            // 定义待插入的数
            // 说明
            // 1. insertIndex >= 0 保证在给insertVal 找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
            // 3. 就需要将arr[insertIndex] 后移
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                // 移位 排序过的元素右移一格
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 当退出while 循环时，说明插入的位置找到, insertIndex + 1
            // 这里我们判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }
            System.out.println("第" + i + "轮插入");
            System.out.println(Arrays.toString(arr));
        }

    }

}
