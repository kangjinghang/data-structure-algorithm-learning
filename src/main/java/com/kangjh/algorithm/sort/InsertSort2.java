package com.kangjh.algorithm.sort;

import java.util.Arrays;

/**
 * InsertSort2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-10
 * @since 1.0.0
 */
public class InsertSort2 {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        int insertValue, insertIndex;
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i - 1;
            insertValue = arr[i];
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertValue;
        }

//        for (int i = 1; i < arr.length; i++) {
//            insertValue = arr[i];
//            insertIndex = i - 1;
//            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
//                arr[insertIndex + 1] = arr[insertIndex];
//                insertIndex--;
//            }
//            arr[insertIndex + 1] = insertValue;
//        }
        System.out.println(Arrays.toString(arr));
    }

}
