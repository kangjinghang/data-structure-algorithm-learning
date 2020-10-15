package com.kangjh.structure.tree;

/**
 * ArrBinaryTreeDemo2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-12
 * @since 1.0.0
 */
public class ArrBinaryTreeDemo2 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        // 创建一个ArrBinaryTree
        ArrBinaryTree2 arrBinaryTree = new ArrBinaryTree2(arr);
        // 1,2,4,5,3,6,7
        arrBinaryTree.postOrder();
    }

}

class ArrBinaryTree2 {

    private int[] arr;

    public ArrBinaryTree2(int[] arr) {
        this.arr = arr;
    }

    public void postOrder() {
        this.preOrder(0);
    }

    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.err.println("数组为空，不能按照二叉树的前序遍历");
        }
        System.out.println(arr[index]);
        int left = index * 2 + 1;
        if (left < arr.length) {
            this.preOrder(left);
        }
        int right = index * 2 + 2;
        if (right < arr.length) {
            this.preOrder(right);
        }
    }

}

