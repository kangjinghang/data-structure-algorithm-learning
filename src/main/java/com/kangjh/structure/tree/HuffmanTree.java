package com.kangjh.structure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树
 *
 * @author kangjinghang
 * @version 1.0
 * @since 2019-07-23
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    /**
     * 创建赫夫曼树
     *
     * @param arr 需要创建成哈夫曼树的数组
     * @return 创建好后的赫夫曼树的root 节点
     */
    public static Node createHuffmanTree(int[] arr) {
        // 第一步为了操作方便
        // 1. 遍历arr 数组
        // 2. 将arr的每个元素构成成一个Node
        // 3. 将Node放入到ArrayList 中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        // 我们处理的过程是一个循环的过程
        while (nodes.size() > 1) {
            // 排序从小到大
            Collections.sort(nodes);
            // 取出根节点权值最小的两颗二叉树
            // (1) 取出权值最小的节点（二叉树）
            Node leftNode = nodes.get(0);
            // (2) 取出权值第二小的节点（二叉树）
            Node rightNode = nodes.get(1);

            // (3)构建一棵新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            // (4)从ArrayList 删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // (5)将parent加入到nodes
            nodes.add(parent);
        }
        //返回哈夫曼树的root节点
        return nodes.get(0);
    }

    /**
     * 前序遍历
     *
     * @param root root节点
     */
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("是空树，不能遍历~~");
        }
    }

}

/**
 * 创建节点类
 * 为了让Node 对象持续排序Collections 集合排序,让Node 实现Comparable 接口
 */
class Node implements Comparable<Node> {

    /**
     * 节点权值
     */
    int value;
    /**
     * 指向左子节点
     */
    Node left;
    /**
     * 指向右子节点
     */
    Node right;

    public Node(int value) {
        this.value = value;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        // 表示从小到大排序
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

}
