package com.kangjh.algorithm.recursion;

/**
 * Queue82
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-09
 * @since 1.0.0
 */
public class Queue82 {

    int max = 8;

    /**
     * 定义数组array, 保存皇后放置位置的结果,比如arr = {0 , 4, 7, 5, 2, 6, 1, 3}
     */
    int[] array = new int[max];

    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        Queue82 queue8 = new Queue82();
        queue8.check(0);
        System.out.printf("一共有%d 解法", count);
        System.out.printf("一共判断冲突的次数%d 次", judgeCount);
    }

    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
