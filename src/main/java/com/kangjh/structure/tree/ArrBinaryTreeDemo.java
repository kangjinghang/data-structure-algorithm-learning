package com.kangjh.structure.tree;

/**
 * @author kangjinghang
 * @version 1.0
 * @since 2019-07-23
 */
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        // 创建一个ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        // 1,2,4,5,3,6,7
        arrBinaryTree.postOrder();
    }

}

/**
 * 编写一个ArrayBinaryTree, 实现顺序存储二叉树遍历
 */
class ArrBinaryTree {
    /**
     * 存储数据结点的数组
     */
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 重载preOrder
     */
    public void preOrder() {
        this.preOrder(0);
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的前序遍历
     *
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        // 如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 输出当前这个元素
        System.out.println(arr[index]);
        // 向左递归遍历
        int leftIndex = index * 2 + 1;
        if (leftIndex < arr.length) {
            preOrder(leftIndex);
        }
        // 向右递归遍历
        int rightIndex = index * 2 + 2;
        if (rightIndex < arr.length) {
            preOrder(rightIndex);
        }

    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的中序遍历
     *
     * @param index 数组的下标
     */
    public void infixOrder(int index) {
        // 如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的中序遍历");
        }
        // 向左递归遍历
        int leftIndex = index * 2 + 1;
        if (leftIndex < arr.length) {
            infixOrder(leftIndex);
        }
        // 输出当前这个元素
        System.out.println(arr[index]);
        // 向右递归遍历
        int rightIndex = index * 2 + 2;
        if (rightIndex < arr.length) {
            infixOrder(rightIndex);
        }

    }

    public void postOrder() {
        this.postOrder(0);
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的后序遍历
     *
     * @param index 数组的下标
     */
    public void postOrder(int index) {
        // 如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的后序遍历");
        }
        // 向左递归遍历
        int leftIndex = index * 2 + 1;
        if (leftIndex < arr.length) {
            postOrder(leftIndex);
        }
        // 向右递归遍历
        int rightIndex = index * 2 + 2;
        if (rightIndex < arr.length) {
            postOrder(rightIndex);
        }
        // 输出当前这个元素
        System.out.println(arr[index]);

    }

}
