package com.kangjh.structure.tree;

/**
 * @author kangjinghang
 * @version 1.0
 * @since 2019-07-23
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        // 测试一把中序线索二叉树的功能
        ThreadedHeroNode root = new ThreadedHeroNode(1, "tom");
        ThreadedHeroNode node2 = new ThreadedHeroNode(3, "jack");
        ThreadedHeroNode node3 = new ThreadedHeroNode(6, "smith");
        ThreadedHeroNode node4 = new ThreadedHeroNode(8, "mary");
        ThreadedHeroNode node5 = new ThreadedHeroNode(10, "king");
        ThreadedHeroNode node6 = new ThreadedHeroNode(14, "dim");
        // 二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        // 测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        // 测试: 以10 号节点测试
        ThreadedHeroNode leftNode = node5.getLeft();
        ThreadedHeroNode rightNode = node5.getRight();
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

/**
 * 线索化二叉树, 实现了线索化功能的二叉树
 */
class ThreadedBinaryTree {

    private ThreadedHeroNode root;

    /**
     * 当前线索化二叉树线索化过的最后一个节点
     * </p>
     * 为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
     * 在递归进行线索化时，pre 总是保留前一个结点
     */
    private ThreadedHeroNode pre = null;

    public void setRoot(ThreadedHeroNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 前序查找
     */
    public ThreadedHeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 中序查找
     */
    public ThreadedHeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 后序查找
     */
    public ThreadedHeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 删除节点
     *
     * @param no no
     */
    public void delNode(int no) {
        if (root != null) {
            // 如果只有一个root 结点, 这里立即判断root 是不是就是要删除结点
            if (root.getNo() == no) {
                root = null;
            } else {
                // 递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除~");
        }
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * 二叉树进行中序线索化的方法
     *
     * @param node 当前需要线索化的结点
     */
    public void threadedNodes(ThreadedHeroNode node) {
        // 如果node==null, 不能线索化
        if (node == null) {
            return;
        }

        // (一)先线索化左子树
        threadedNodes(node.getLeft());

        // (二)线索化当前结点
        // 处理当前结点的前驱结点
        // 以 8 结点来理解
        //  8 结点的.left = null , 8 结点的.leftType = 1
        if (node.getLeft() == null) {
            // 让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        // 处理后继结点
        if (pre != null && pre.getRight() == null) {
            // 让树的前驱结点的右指针指向当前结点
            pre.setRight(node);
            // 修改树的前驱结点的右指针类型
            pre.setRightType(1);
        }

        // !!! 每处理一个结点后，将当前结点设置为这棵树的前驱结点
        pre = node;

        // (三)再线索化右子树
        threadedNodes(node.getRight());
    }

    /**
     * 遍历线索化二叉树的方法
     */
    public void threadedList() {
        // 定义一个变量，存储当前遍历的结点，从 root 开始
        // 循环: 前驱节点 --> 本节点 --> 所有的后继节点
        ThreadedHeroNode node = root;
        while(node != null){
            // 循环的找到 leftType == 1 的结点，第一个找到就是 8 结点
            // 后面随着遍历而变化,因为当 leftType==1 时，说明该结点是按照线索化处理后的有效结点
            while(node.getLeftType() == 0){
                node = node.getLeft();
            }
            // 打印当前这个结点
            System.out.println(node);
            // 如果当前结点的右指针指向的是后继结点,就一直输出
            while(node.getRightType() == 1) {
                // 获取到当前结点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历的结点为右子树类型节点
            node = node.getRight();
        }
    }

}

class ThreadedHeroNode {

    private int no;

    private String name;

    /**
     * 默认null
     */
    private ThreadedHeroNode left;

    /**
     * 默认null
     */
    private ThreadedHeroNode right;

    /**
     * 如果leftType == 0 表示指向的是左子树, 如果 1 则表示指向前驱结点
     */
    private int leftType;

    /**
     * 如果rightType == 0 表示指向是右子树, 如果 1 则表示指向后继结点
     */
    private int rightType;

    public ThreadedHeroNode(int no, String name) {
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

    public ThreadedHeroNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedHeroNode left) {
        this.left = left;
    }

    public ThreadedHeroNode getRight() {
        return right;
    }

    public void setRight(ThreadedHeroNode right) {
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
        return "ThreadedHeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        // 输出父结点
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出父结点
        System.out.println(this);
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        // 输出父结点
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param no no
     */
    public ThreadedHeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历");
        // 比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        // 1.则判断当前结点的左子节点是否为空，如果不为空，则递归前序查找
        // 2.如果左递归前序查找，找到结点，则返回
        ThreadedHeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        // 说明我们左子树找到
        if (resNode != null) {
            return resNode;
        }
        // 1.左递归前序查找，找到结点，则返回，否继续判断
        // 2.当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     *
     * @param no no
     */
    public ThreadedHeroNode infixOrderSearch(int no) {
        // 判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
        ThreadedHeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序遍历");
        // 如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点
        if (this.no == no) {
            return this;
        }
        // 否则继续进行右递归的中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序遍历查找
     *
     * @param no no
     */
    public ThreadedHeroNode postOrderSearch(int no) {
        // 判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
        ThreadedHeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            // 说明在左子树找到
            return resNode;
        }
        // 如果左子树没有找到，则向右子树递归进行后序遍历查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序查找");
        // 如果左右子树都没有找到，就比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        return null;
    }

    /**
     * 递归删除结点
     * 1.如果删除的节点是叶子节点，则删除该节点
     * 2.如果删除的节点是非叶子节点，则删除该子树
     *
     * @param no no
     */
    public void delNode(int no) {

        /*
         * 思路
         * 1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
         * 2. 如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
         * 3. 如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right= null; 并且就返回(结束递归删除)
         * 4. 如果第2步和第3步没有删除结点，那么我们就需要向左子树进行递归删除
         * 5. 如果第4步也没有删除结点，则应当向右子树进行递归删除.
         */
        // 2.如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        // 3. 如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right= null; 并且就返回(结束递归删除)
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 4.我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        // 5.则应当向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

}
