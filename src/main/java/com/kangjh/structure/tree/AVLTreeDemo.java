package com.kangjh.structure.tree;

/**
 * @author kangjinghang
 * @version 1.0
 * @since 2019-07-28
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        // 创建一个 AVLTree对象
        AVLTree avlTree = new AVLTree();
        // 添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new AVLNode(arr[i]));
        }

        // 遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight());
        System.out.println("当前的根节点=" + avlTree.getRoot());
    }

}

/**
 * 创建平衡二叉树
 */
class AVLTree {

    private AVLNode root;

    public AVLNode getRoot() {
        return root;
    }

    /**
     * 添加节点的方法
     *
     * @param node node
     */
    public void add(AVLNode node) {
        // 如果root为空则直接让root指向node
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

    /**
     * 查找要删除的节点
     */
    public AVLNode search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    /**
     * 查找父节点
     */
    public AVLNode searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 1. 返回的 以node 为根节点的二叉排序树的最小节点的值
     * 2. 删除node 为根节点的二叉排序树的最小节点
     *
     * @param node 传入的节点(当做二叉排序树的根节点)
     * @return 返回的 以node 为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(AVLNode node) {
        AVLNode target = node;
        // 循环的查找左子节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 这时 target就指向了最小节点
        // 删除最小节点
        delNode(target.value);
        return target.value;
    }

    /**
     * 删除节点
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 先去找到要删除的节点  targetNode
            AVLNode targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            // 如果我们发现当前这棵二叉排序树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            // 去找到targetNode的父节点
            AVLNode parent = searchParent(value);
            // 如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode 是父节点的左子节点，还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    // 是左子节点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    // 是右子节点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                // 删除有两颗子树的节点
                targetNode.value = delRightTreeMin(targetNode.right);
            } else {
                // 删除只有一颗子树的节点
                // 如果要删除的节点有左子节点
                if (targetNode.left != null) {
                    if (parent != null) {
                        // 如果 targetNode 是 parent 的左子节点
                        if (parent.left.value == targetNode.value) {
                            parent.left = targetNode.left;
                        } else {
                            // targetNode 是 parent 的右子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    // 如果要删除的节点有右子节点
                    if (parent != null) {
                        // 如果 targetNode 是 parent 的左子节点
                        if (parent.left.value == targetNode.value) {
                            parent.left = targetNode.right;
                        } else {
                            // targetNode 是 parent 的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

}

/**
 * 创建Node节点
 */
class AVLNode {

    int value;

    AVLNode left;

    AVLNode right;

    public AVLNode(int value) {
        this.value = value;
    }

    /**
     * 返回左子树的高度
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 返回右子树的高度
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 返回以该节点为根节点的树的高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 左旋转方法
     */
    private void leftRotate() {
        // 创建新的节点，以当前根节点的值
        AVLNode newNode = new AVLNode(this.value);
        // 把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        // 把新的节点的右子树设置成带你过去节点的右子树的左子树
        newNode.right = right.left;
        // 把当前节点的值替换成右子节点的值
        value = right.value;
        // 把当前节点的右子树设置成当前节点右子树的右子树
        right = right.right;
        // 把当前节点的左子树(左子节点)设置成新的节点
        left = newNode;
    }

    /**
     * 右旋转方法
     */
    private void rightRotate() {
        // 创建新的节点，以当前根节点的值
        AVLNode newNode = new AVLNode(this.value);
        // 把新的节点的右子树设置成当前节点的右子树
        newNode.right = right;
        // 把新的节点的左子树设置成带你过去节点的左子树的右子树
        newNode.left = left.right;
        // 把当前节点的值替换成左子节点的值
        value = left.value;
        // 把当前节点的左子树设置成当前节点左子树的左子树
        left = left.left;
        // 把当前节点的右子树(右子节点)设置成新的节点
        right = newNode;
    }

    /**
     * 查找要删除的节点
     *
     * @param value 希望删除的节点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public AVLNode search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            // 如果查找的值小于当前节点，向左子树递归查找
            // 如果左子节点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            // 如果查找的值不小于当前节点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     *
     * @param value 要找到的节点的值
     * @return 返回的是要删除的节点的父节点，如果没有就返回null
     */
    public AVLNode searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值小于当前节点的值, 并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                // 向左子树递归查找
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                // 向右子树递归查找
                return this.right.searchParent(value);
            } else {
                // 没有找到父节点
                return null;
            }
        }
    }

    /**
     * 添加节点的方法
     * 递归的形式添加节点，注意需要满足二叉排序树的要求
     */
    public void add(AVLNode node) {
        if (node == null) {
            return;
        }
        // 判断传入的节点的值，和当前子树的根节点的值关系
        if (node.value < this.value) {
            // 如果当前节点左子节点为null
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归的向左子树添加
                this.left.add(node);
            }
        } else {  // 添加的节点的值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                // 递归的向右子树添加
                this.right.add(node);
            }
        }

        // 当添加完一个节点后，如果: (右子树的高度-左子树的高度) > 1 , 左旋转
        if (rightHeight() - leftHeight() > 1) {
            // 如果它的右子树的左子树的高度大于它的右子树的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                // 先对当前节点的右节点(右子树)->右旋转
                right.rightRotate();
            }
            leftRotate();
            // 必须要!!!
            return;
        }

        // 当添加完一个节点后，如果 (左子树的高度 - 右子树的高度) > 1, 右旋转
        if (leftHeight() - rightHeight() > 1) {
            // 如果它的左子树的右子树高度大于它的左子树的左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                // 先对当前节点的左节点(左子树)->左旋转
                left.leftRotate();
            }
            rightRotate();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "AVLNode{" +
                "value=" + value +
                '}';
    }

}
