package com.kangjh.structure.linkedlist;

/**
 * DoubleLinkedListDemo2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-08
 * @since 1.0.0
 */
public class DoubleLinkedListDemo2 {

    public static void main(String[] args) {
        // 先创建节点
        HeroNode4 hero1 = new HeroNode4(1, "宋江", "及时雨");
        HeroNode4 hero2 = new HeroNode4(2, "卢俊义", "玉麒麟");
        HeroNode4 hero3 = new HeroNode4(3, "吴用", "智多星");
        HeroNode4 hero4 = new HeroNode4(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList2 doubleLinkedList = new DoubleLinkedList2();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);
        // 加入按照编号的顺序
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);


        doubleLinkedList.list();
//
//        // 修改
//        HeroNode4 newHeroNode = new HeroNode4(4, "公孙胜", "入云龙");
//        doubleLinkedList.update(newHeroNode);
//        System.out.println("修改后的链表情况");
//        doubleLinkedList.list();
//
//        // 删除
//        doubleLinkedList.del(3);
//        System.out.println("删除后的链表情况~~");
//        doubleLinkedList.list();
    }

}

class DoubleLinkedList2 {

    private HeroNode4 head = new HeroNode4(0, "", "");

    public HeroNode4 getHead() {
        return head;
    }

    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // HeroNode4，不能动，因此我们需要一个辅助变量来遍历
        HeroNode4 temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void add(HeroNode4 heroNode) {
        HeroNode4 current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = heroNode;
        heroNode.pre = current;
    }

    public void update(HeroNode4 heroNode) {
        HeroNode4 current = head.next;
        if (current == null) {
            return;
        }
        while (true) {
            if (current == null) {
                break;
            }
            if (current.no == heroNode.no) {
                current.name = heroNode.name;
                current.nickname = heroNode.nickname;
                break;
            }
            current = current.next;
        }
    }

    public void del(int no) {
        if (head.next == null) {
            return;
        }
        HeroNode4 current = head;
        while (true) {
            if (current == null) {
                break;
            }
            if (current.no == no) {
                current.pre.next = current.next;
                if (current.next != null) {
                    current.next.pre = current.pre;
                }
                System.out.println("找到了，已删除");
                break;
            }
            current = current.next;
        }
    }

    public void addByOrder(HeroNode4 heroNode) {
        if (head.next == null) {
            head.next = heroNode;
            heroNode.pre = head;
            return;
        }
        boolean flag = false;
        HeroNode4 current = head.next;

        while (true) {
            if (current.next == null) {
                break;
            }
            if (current.no == heroNode.no) {
                System.err.println("有问题");
                return;
            }
            if (current.next.no > heroNode.no) {
                flag = true;
                break;
            }
            current = current.next;
        }
        if (flag) {
            heroNode.next = current.next;
            current.next.pre = heroNode;
            heroNode.pre = current;
            current.next = heroNode;
        } else {
            current.next = heroNode;
            heroNode.pre = current;
        }
    }

}

class HeroNode4 {

    public int no;

    public String name;

    public String nickname;

    /**
     * 指向下一个节点, 默认为null
     */
    public HeroNode4 next;

    /**
     * 指向前一个节点, 默认为null
     */
    public HeroNode4 pre;

    public HeroNode4(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';

    }

}