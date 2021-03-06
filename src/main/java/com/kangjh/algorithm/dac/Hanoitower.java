package com.kangjh.algorithm.dac;

/**
 * 汉诺塔问题
 *
 * @author kangjinghang
 * @version 1.0
 * @since 2019-08-07
 */
public class Hanoitower {

    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }

    /**
     * 汉诺塔的移动的方法
     * 使用分治算法
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            hanoiTower(num - 1, a, c, b);
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            hanoiTower(num - 1, b, c, a);
        }

    }

//    public static void hanoiTower(int num, char a, char b, char c) {
//        //如果只有一个盘
//        if (num == 1) {
//            System.out.println("第1个盘从" + a + "->" + c);
//        } else {
//            //如果我们有n >= 2 情况，我们总是可以看做是两个盘1.最下边的一个盘2. 上面的所有盘
//            //1. 先把最上面的所有盘A->B， 移动过程会使用到c
//            hanoiTower(num - 1, a, c, b);
//            //2. 把最下边的盘A->C
//            System.out.println("第" + num + "个盘从" + a + "->" + c);
//            //3. 把B 塔的所有盘从B->C , 移动过程使用到a 塔
//            hanoiTower(num - 1, b, a, c);
//        }

}
