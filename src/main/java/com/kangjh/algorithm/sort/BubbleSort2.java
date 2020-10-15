package com.kangjh.algorithm.sort;

import java.util.Arrays;

/**
 * BubbleSort2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-09
 * @since 1.0.0
 */
public class BubbleSort2 {

    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, -2};
        int temp = 0;
        boolean flag = false;
        for (int i = 0; !flag && i < arr.length - 1; flag = false, i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
        }

//        for (int i = 0; i < arr.length - 1; i++) {
//            for (int j = 0; j < arr.length - 1 - i; j++) {
//                if (arr[j] > arr[j + 1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                    flag = true;
//                }
//            }
//            if (!flag) {
//                break;
//            } else {
//                flag = false;
//            }
//        }
        System.out.println("arr: " + Arrays.toString(arr));
    }

}
