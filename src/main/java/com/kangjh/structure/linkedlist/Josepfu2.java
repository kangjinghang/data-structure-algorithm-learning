package com.kangjh.structure.linkedlist;

/**
 * Josepfu2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-08
 * @since 1.0.0
 */
public class Josepfu2 {

    public static void main(String[] args) {
        CircleSingleLinkedList2 circleSingleLinkedList = new CircleSingleLinkedList2();
        // 加入5 个小孩节点
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();
        // 2->4->1->5->3
        circleSingleLinkedList.countBoy(1, 2, 5);
    }

}

class CircleSingleLinkedList2 {

    public Boy first = null;

    public void addBoy(int nums) {
        if (nums < 1) {
            System.err.println("nums的值错误");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.next = first;
                curBoy = first;
                continue;
            }
            curBoy.next = boy;
            boy.next = first;
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
        Boy helper = first;
        while (true) {
            if (helper.next == first) {
                break;
            }
            helper = helper.next;
        }
        for (int i = 0; i < startNo - 1; i++) {
            first = first.next;
            helper = helper.next;
        }

        while (true) {
            if (helper == first) {
                break;
            }
            for (int i = 1; i < countNum; i++) {
                first = first.next;
                helper = helper.next;
            }
            System.out.printf("小孩%d 出圈\n", first.no);
            first = first.next;
            helper.next = first;
        }

    }


    public void showBoy() {
        if (first == null) {
            System.out.println("当前链表为空");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", curBoy.no);
            if (curBoy.next == first) {
                break;
            }
            curBoy = curBoy.next;
        }
    }

}
