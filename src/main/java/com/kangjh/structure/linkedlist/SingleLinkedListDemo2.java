package com.kangjh.structure.linkedlist;

import java.util.Stack;

/**
 * SingleLinkedListDemo2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-07
 * @since 1.0.0
 */
public class SingleLinkedListDemo2 {

    public static void main(String[] args) {
        // 先创建节点
        HeroNode3 hero1 = new HeroNode3(1, "宋江", "及时雨");
        HeroNode3 hero2 = new HeroNode3(2, "卢俊义", "玉麒麟");
        HeroNode3 hero3 = new HeroNode3(4, "吴用", "智多星");
        HeroNode3 hero4 = new HeroNode3(8, "林冲", "豹子头");
        // 创建要给链表
        SingleLinkedList2 singleLinkedList = new SingleLinkedList2();
        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);

        // 加入按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        singleLinkedList.list();

        // 测试修改节点的代码
        HeroNode3 newHeroNode = new HeroNode3(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况~~");
        singleLinkedList.list();
        System.out.println("length:" + singleLinkedList.getLength());

//        singleLinkedList.del(1);
//        System.out.println("length:" + singleLinkedList.getLength());
//
//        singleLinkedList.del(4);
//        System.out.println("length:" + singleLinkedList.getLength());
//        System.out.println("删除后的链表情况~~");
        singleLinkedList.list();

        HeroNode3 lastIndexNode = singleLinkedList.findLastIndexNode(5);
        System.out.println(lastIndexNode);
        lastIndexNode = singleLinkedList.findLastIndexNode(4);
        System.out.println(lastIndexNode);
        lastIndexNode = singleLinkedList.findLastIndexNode(1);
        System.out.println(lastIndexNode);


//        System.out.println("翻转了");
//        singleLinkedList.reverse();
//        singleLinkedList.list();

        System.out.println("倒序输出");
        singleLinkedList.reversePrint();


        HeroNode3 hero5 = new HeroNode3(3, "宋江", "及时雨");
        HeroNode3 hero6 = new HeroNode3(5, "卢俊义", "玉麒麟");
        HeroNode3 hero7 = new HeroNode3(9, "吴用", "智多星");
        HeroNode3 hero8 = new HeroNode3(12, "林冲", "豹子头");
        HeroNode3 hero9 = new HeroNode3(15, "林冲", "豹子头");
        HeroNode3 hero10 = new HeroNode3(22, "林冲", "豹子头");
        // 创建要给链表
        SingleLinkedList2 singleLinkedList2 = new SingleLinkedList2();

        // 加入按照编号的顺序
        singleLinkedList2.addByOrder(hero5);
        singleLinkedList2.addByOrder(hero6);
        singleLinkedList2.addByOrder(hero7);
        singleLinkedList2.addByOrder(hero8);
        singleLinkedList2.addByOrder(hero9);
        singleLinkedList2.addByOrder(hero10);
        System.out.println("list2 加好了");
        singleLinkedList2.list();

        System.out.println("合并好了");
        singleLinkedList.merge(singleLinkedList2);
        singleLinkedList.list();
    }

}

class SingleLinkedList2 {

    public HeroNode3 head = new HeroNode3(0, "", "");

    public void add(HeroNode3 heroNode) {
        HeroNode3 current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = heroNode;
    }

    public void addByOrder(HeroNode3 heroNode) {
        HeroNode3 current = head.next;
        if (current == null) {
            head.next = heroNode;
            return;
        }
        while (current.next != null && current.next.no < heroNode.no) {
            current = current.next;
        }
        if (current.next == null) {
            current.next = heroNode;
            return;
        }
        if (current.next.no == heroNode.no) {
            System.err.println("有问题");
            return;
        }
        heroNode.next = current.next;
        current.next = heroNode;
    }

    public void update(HeroNode3 heroNode) {
        HeroNode3 current = head.next;
        if (current == null) {
            return;
        }
        while (true) {
            if (current.next == null) {
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

    public void reversePrint() {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode3> stack = new Stack<>();
        HeroNode3 temp = head.next;
        while (temp != null) {
            stack.add(temp);
            temp = temp.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public void merge(SingleLinkedList2 anotherList) {
        HeroNode3 anotherHead = anotherList.head;
        if (anotherHead.next == null) {
            return;
        }
        SingleLinkedList2 mergedList = new SingleLinkedList2();
        while (head.next != null) {
            while (anotherHead.next != null) {
                if (head.next != null && head.next.no < anotherHead.next.no) {
                    HeroNode3 target = head.next;
                    head.next = target.next;
                    target.next = null;
                    mergedList.add(target);
                } else {
                    HeroNode3 target = anotherHead.next;
                    anotherHead.next = target.next;
                    target.next = null;
                    mergedList.add(target);
                }
            }
        }
        while (anotherHead.next != null) {
            HeroNode3 target = anotherHead.next;
            anotherHead.next = target.next;
            target.next = null;
            mergedList.add(target);
        }
        head = mergedList.head;
    }

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode3 current = head;
        while (current.next != null) {
            System.out.println(current);
            current = current.next;
        }
        System.out.println(current);
    }

    public HeroNode3 pop() {
        if (head.next == null) {
            System.out.println("链表为空");
            return null;
        }
        HeroNode3 target = head.next;
        head.next = target.next;
        return target;
    }

    public void del(int no) {
        HeroNode3 current = head;
        if (current.next == null) {
            return;
        }
        if (current.next.no == no) {
            head.next = current.next.next;
            System.out.println("找到了，已删除");
            return;
        }
        while (current.next != null) {
            if (current.next.no == no) {
                current.next = current.next.next;
                System.out.println("找到了，已删除");
                break;
            }
            current = current.next;
        }
        System.out.println("没找到");
    }

    public void reverse() {
        HeroNode3 reverseHead = new HeroNode3(0, "", "");
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode3 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            HeroNode3 next = temp.next;
            temp.next = reverseHead.next;
            reverseHead.next = temp;
            temp = next;
        }
        head = reverseHead;
    }

    public HeroNode3 findLastIndexNode(int index) {
        int length = getLength();
        if (index <= 0 || index > length) {
            return null;
        }
        HeroNode3 temp = head;
        for (int i = 0; i <= length - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public int getLength() {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode3 temp = head.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

}

class HeroNode3 {

    public int no;

    public String name;

    public String nickname;

    /**
     * 指向下一个节点
     */
    public HeroNode3 next;

    public HeroNode3(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode3{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }

}
