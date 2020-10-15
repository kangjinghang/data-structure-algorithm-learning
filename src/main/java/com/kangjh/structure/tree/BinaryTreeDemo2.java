package com.kangjh.structure.tree;

/**
 * BinaryTreeDemo2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-12
 * @since 1.0.0
 */
public class BinaryTreeDemo2 {

    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree2 binaryTree = new BinaryTree2();
        //创建需要的结点
        HeroNode2 root = new HeroNode2(1, "宋江");
        HeroNode2 node2 = new HeroNode2(2, "吴用");
        HeroNode2 node3 = new HeroNode2(3, "卢俊义");
        HeroNode2 node4 = new HeroNode2(4, "林冲");
        HeroNode2 node5 = new HeroNode2(5, "关胜");

        //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //测试
        System.out.println("前序遍历");
        // 1,2,3,5,4
        binaryTree.preOrder();

        System.out.println("中序遍历");
        // 2,1,5,3,4
        binaryTree.infixOrder();

        System.out.println("后序遍历");
        // 2,5,4,3,1
        binaryTree.postOrder();


        System.out.println();
        System.out.println("--------------------------");

        int no = 5;
        // 前序遍历
        // 前序遍历的次数：4
        System.out.println("前序遍历方式~~~");
        HeroNode2 resNode = binaryTree.preOrderSearch(no);
        if (resNode != null) {
            System.out.printf("找到了，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到no = %d 的英雄", no);
        }

        System.out.println();
        System.out.println("--------------------------");

        // 中序遍历查找
        // 中序遍历3 次
        System.out.println("中序遍历方式~~~");
        resNode = binaryTree.infixOrderSearch(no);
        if (resNode != null) {
            System.out.printf("找到了，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到no = %d 的英雄", no);
        }

        System.out.println();
        System.out.println("--------------------------");

        //后序遍历查找
        //后序遍历查找的次数2 次
        System.out.println("后序遍历方式~~~");
        resNode = binaryTree.postOrderSearch(no);
        if (resNode != null) {
            System.out.printf("找到了，信息为no=%d name=%s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到no = %d 的英雄", no);
        }

        System.out.println();
        System.out.println("--------------------------");

        System.out.println("删除前,前序遍历");
        binaryTree.preOrder(); // 1,2,3,5,4
        binaryTree.delNode(5);
        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder(); // 1,2,3,4
    }

}

class BinaryTree2 {

    private HeroNode2 root;

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }

    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.err.println("二叉树为空，不能遍历");
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.err.println("二叉树为空，不能遍历");
        }
    }

    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.err.println("二叉树为空，不能遍历");
        }
    }

    public HeroNode2 preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode2 infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }


    public HeroNode2 postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.err.println("空树，不能删除~");
        }
    }

}

class HeroNode2 {

    private int no;

    private String name;

    /**
     * 默认null
     */
    private HeroNode2 left;

    /**
     * 默认null
     */
    private HeroNode2 right;

    public HeroNode2(int no, String name) {
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

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
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

    public void infixOrder() {
        if (this.left != null) {
            this.left.preOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.postOrder();
        }
    }

    public HeroNode2 preOrderSearch(int no) {
        System.out.println("进入前序遍历");
        if (this.no == no) {
            return this;
        }
        HeroNode2 result = null;
        if (this.left != null) {
            result = this.left.preOrderSearch(no);
        }
        if (result != null) {
            return result;
        }
        if (this.right != null) {
            result = this.right.preOrderSearch(no);
        }
        return result;
    }

    public HeroNode2 infixOrderSearch(int no) {
        HeroNode2 result = null;
        if (this.left != null) {
            result = this.left.preOrderSearch(no);
        }
        if (result != null) {
            return result;
        }
        System.out.println("进入中序遍历");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            result = this.right.preOrderSearch(no);
        }
        return result;
    }

    public HeroNode2 postOrderSearch(int no) {
        HeroNode2 result = null;
        if (this.left != null) {
            result = this.left.preOrderSearch(no);
        }
        if (result != null) {
            return result;
        }
        if (this.right != null) {
            result = this.right.preOrderSearch(no);
        }
        if (result != null) {
            return result;
        }
        System.out.println("进入后序查找");
        if (this.no == no) {
            return this;
        }
        return result;
    }

    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

}