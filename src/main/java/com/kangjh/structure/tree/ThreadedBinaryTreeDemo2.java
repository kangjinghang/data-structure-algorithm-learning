package com.kangjh.structure.tree;

/**
 * ThreadedBinaryTreeDemo2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-12
 * @since 1.0.0
 */
public class ThreadedBinaryTreeDemo2 {

    public static void main(String[] args) {
        // 测试一把中序线索二叉树的功能
        ThreadedHeroNode2 root = new ThreadedHeroNode2(1, "tom");
        ThreadedHeroNode2 node2 = new ThreadedHeroNode2(3, "jack");
        ThreadedHeroNode2 node3 = new ThreadedHeroNode2(6, "smith");
        ThreadedHeroNode2 node4 = new ThreadedHeroNode2(8, "mary");
        ThreadedHeroNode2 node5 = new ThreadedHeroNode2(10, "king");
        ThreadedHeroNode2 node6 = new ThreadedHeroNode2(14, "dim");
        // 二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        // 测试中序线索化
        ThreadedBinaryTree2 threadedBinaryTree = new ThreadedBinaryTree2();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        // 测试: 以10 号节点测试
        ThreadedHeroNode2 leftNode = node5.getLeft();
        ThreadedHeroNode2 rightNode = node5.getRight();
        // 3
        System.out.println("10 号结点的前驱结点是=" + leftNode);
        // 1
        System.out.println("10 号结点的后继结点是=" + rightNode);

        // 当线索化二叉树后，能在使用原来的遍历方法
        //threadedBinaryTree.infixOrder();
        System.out.println("使用线索化的方式遍历线索化二叉树");
        // 8, 3, 10, 1, 14, 6
        threadedBinaryTree.threadedList();
    }

}

class ThreadedBinaryTree2 {

    private ThreadedHeroNode2 root;

    private ThreadedHeroNode2 pre = null;

    public void setRoot(ThreadedHeroNode2 root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    public void threadedNodes(ThreadedHeroNode2 node) {
        if (node == null) {
            return;
        }
        threadedNodes(node.getLeft());

        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        threadedNodes(node.getRight());
    }

    public void threadedList() {
        ThreadedHeroNode2 node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

}

class ThreadedHeroNode2 {

    private int no;

    private String name;

    private ThreadedHeroNode2 left;

    private ThreadedHeroNode2 right;

    /**
     * 如果leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
     */
    private int leftType;

    /**
     * 如果rightType == 0 表示指向是右子树, 如果 1 则表示指向后继结点
     */
    private int rightType;

    public ThreadedHeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThreadedHeroNode2 getLeft() {
        return left;
    }

    public void setLeft(ThreadedHeroNode2 left) {
        this.left = left;
    }

    public ThreadedHeroNode2 getRight() {
        return right;
    }

    public void setRight(ThreadedHeroNode2 right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "ThreadedHeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

}
