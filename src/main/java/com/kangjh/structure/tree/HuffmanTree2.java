package com.kangjh.structure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * HuffmanTree2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-12
 * @since 1.0.0
 */
public class HuffmanTree2 {

    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node2 root = createHuffmanTree(arr);
        preOrder(root);
    }

    private static void preOrder(Node2 root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.err.println("空树，不能遍历");
        }
    }

    private static Node2 createHuffmanTree(int[] arr) {
        List<Node2> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node2(value));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node2 left = nodes.get(0);
            Node2 right = nodes.get(1);
            Node2 parent = new Node2(left.value + right.value);
            parent.left = left;
            parent.right = right;
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

}

class Node2 implements Comparable<Node2> {

    public int value;

    public Node2 left;

    public Node2 right;

    public Node2(int value) {
        this.value = value;
    }

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
    public int compareTo(Node2 o) {
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node2{" +
                "value=" + value +
                '}';
    }

}
