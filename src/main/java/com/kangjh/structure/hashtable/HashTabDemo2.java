package com.kangjh.structure.hashtable;

import java.util.Scanner;

/**
 * HashTabDemo2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-11
 * @since 1.0.0
 */
public class HashTabDemo2 {

    public static void main(String[] args) {
        HashTab2 hashTab = new HashTab2(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }

}

class HashTab2 {

    private EmpLinkedList2[] empLinkedListArray;

    private int size;

    public HashTab2(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList2[size];
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList2();
        }
    }

    public void add(Emp emp) {
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public void findEmpById(int id) {
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if (emp != null) {
            // 找到
            System.out.printf("在第%d条链表中找到雇员id = %d\n", (empLinkedListNo + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    public int hashFun(int id) {
        return id % size;
    }

}

class EmpLinkedList2 {

    private Emp head;

    public void add(Emp emp) {
        if (null == head) {
            head = emp;
        } else {
            Emp temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = head;
        }

    }

    public void list(int no) {
        if (head == null) {
            System.out.println("第" + (no + 1) + " 链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + " 链表的信息为");
        Emp curEmp = head;
        while (true) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    public Emp findEmpById(int id) {
        Emp temp = head;
        while (temp.next != null) {
            if (temp.id == id) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

}
