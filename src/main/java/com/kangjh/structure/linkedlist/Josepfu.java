package com.kangjh.structure.linkedlist;

/**
 * 约瑟夫问题
 *
 * @author kangjinghang
 * @version 1.0
 * @since 2019-08-03
 */
public class Josepfu {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        // 加入5 个小孩节点
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        // 2->4->1->5->3
        circleSingleLinkedList.countBoy(1, 2, 2);
    }

}

/**
 * 创建一个环形的单向链表
 */
class CircleSingleLinkedList {

    /**
     * 创建一个first 节点,当前没有编号
     */
    private Boy first = null;

    /**
     * 添加小孩节点，构建成一个环形的链表
     */
    public void addBoy(int nums) {
        // nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums 的值不正确");
            return;
        }
        // 辅助指针，帮助构建环形链表
        Boy curBoy = null;
        // 使用for 来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号，创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1) {
                first = boy;
                // 构成环
                first.next = boy;
                // 让curBoy 指向第一个小孩
                curBoy = first;
            } else {
                curBoy.next = boy;
                boy.next = first;
                curBoy = boy;
            }
        }
    }

    /**
     * 遍历当前的环形链表
     */
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩~~");
            return;
        }
        // 因为first 不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", curBoy.no);
            if (curBoy.next == first) {
                // 说明已经遍历完毕
                break;
            }
            // curBoy 后移
            curBoy = curBoy.next;
        }
    }

    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        // 创建要给辅助指针,帮助完成小孩出圈
        // 创建一个辅助指针(变量) helper , 事先应该指向环形链表的最后这个节点
        Boy helper = first;
        while (true) {
            // 说明helper 指向最后小孩节点
            if (helper.next == first) {
                break;
            }
            helper = helper.next;
        }
        //小孩报数前，先让first 和helper 移动k - 1 次
        for (int j = 0; j < startNo - 1; j++) {
            first = first.next;
            helper = helper.next;
        }
        //当小孩报数时，让first 和helper 指针同时的移动m - 1 次, 然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (true) {
            if (helper == first) {
                //说明圈中只有一个节点
                break;
            }
            //让first 和helper 指针同时的移动countNum - 1
            for (int j = 0; j < countNum - 1; j++) {
                first = first.next;
                helper = helper.next;
            }
            //这时first 指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d 出圈\n", first.no);
            //这时将first 指向的小孩节点出圈
            first = first.next;
            helper.next = first;
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.no);
    }

}

/**
 * 创建一个Boy 类，表示一个节点
 */
class Boy {

    /**
     * 编号
     */
    public int no;

    /**
     * 指向下一个节点,默认null
     */
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }

}
