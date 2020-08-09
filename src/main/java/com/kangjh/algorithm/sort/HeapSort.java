package com.kangjh.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author kangjinghang
 * @version 1.0
 * @since 2019-07-23
 */
public class HeapSort {

    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 5, 9};
        heapSort(arr);
        System.out.println("排序后=" + Arrays.toString(arr));
    }

    public static void heapSort(int arr[]) {
        int temp = 0;
        // 1.将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        /*
         * 2.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
         * 3.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
         */
        for (int j = arr.length - 1; j > 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 将一个数组(二叉树), 调整成一个大顶堆
     * <p>
     * 功能： 完成将以 i 对应的非叶子节点的树调整成大顶堆
     * 举例int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到{4, 9, 8, 5, 6}
     * 如果我们再次调用adjustHeap 传入的是i = 0 => 得到{4, 9, 8, 5, 6} => {9,6,8,5, 4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中索引
     * @param length 表示对多少个元素继续调整， length 是在逐渐的减少
     */
    public static void adjustHeap(int arr[], int i, int length) {
        // 先取出当前元素的值，保存在临时变量
        int temp = arr[i];

        //1. k = i * 2 + 1, k 是 i 节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 说明左子节点的值小于右子节点的值
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // k 指向右子节点
                k++;
            }
            // 如果子节点大于父节点
            if (arr[k] > temp) {
                // 把较大的值赋给当前节点
                arr[i] = arr[k];
                // !!! i 指向k,继续循环比较
                i = k;
            } else {
                break;
            }
        }
        // 当for循环结束后，我们已经将以 i 为父节点的树的最大值，放在了最顶(局部)
        // 将 temp 值放到调整后的位置
        arr[i] = temp;
    }

}
