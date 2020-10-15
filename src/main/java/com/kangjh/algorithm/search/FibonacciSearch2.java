package com.kangjh.algorithm.search;

import java.util.Arrays;

/**
 * FibonacciSearch2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-11
 * @since 1.0.0
 */
public class FibonacciSearch2 {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("index=" + fibSearch(arr, 89));
    }

    public static int maxSize = 20;

    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int fibSearch(int[] arr, int findValue) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;
        int[] f = fib();
        while (f[k] - 1 < high) {
            k++;
        }
        int[] temp = Arrays.copyOf(arr, f[k]);
        for (int i = arr.length; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        int mid;
        while (low <= high) {
            mid = low + f[k - 1] + 1;
            if (findValue < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (findValue > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

}



