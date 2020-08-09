package com.kangjh.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author kangjinghang
 * @version 1.0
 * @since 2019-07-19
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        // 左索引
        int l = left;
        // 右索引
        int r = right;
        int pivot = arr[(left + right) / 2];
        // 临时变量，作为交换时使用
        int temp;
        // while 循环的目的是让比pivot的值小的放到左边，比pivot值大的放到右边
        while (l < r) {
            // 在pivot的左边一直找,找到大于等于pivot的值,才退出
            while (arr[l] < pivot) {
                l++;
            }
            // 在pivot的右边一直找,找到小于等于pivot的值,才退出
            while (arr[r] > pivot) {
                r--;
            }
            // 如果l >= r 说明pivot的左右两边的值，已经按照左边全部是小于等于pivot的值，右边全部是大于等于pivot的值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 如果交换完后，发现这个arr[l] == pivot 值相等r--， 前移
            if (arr[l] == pivot) {
                r--;
            }
            // 如果交换完后，发现这个arr[r] == pivot 值相等l++， 后移
            if (arr[r] == pivot) {
                l++;
            }
            // 如果l == r, 必须l++, r--, 否则为出现栈溢出
            if (l == r) {
                l++;
                r--;
            }
            // 向左递归
            if (left < r) {
                quickSort(arr, left, r);
            }
            // 向右递归
            if (right > l) {
                quickSort(arr, l, right);
            }
        }

    }

}
