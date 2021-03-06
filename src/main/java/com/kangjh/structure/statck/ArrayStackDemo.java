package com.kangjh.structure.statck;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 栈
 *
 * @author kangjinghang
 * @version 1.0
 * @since 2019-08-05
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        //先创建一个ArrayStack 对象->表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }

}

/**
 * 定义一个ArrayStack 表示栈
 */
class ArrayStack {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            if (map.containsKey(target - a)) {
                return new int[]{i, map.get(target - a)};
            }
        }
        throw new RuntimeException("Answer not found");
    }


    /**
     * 栈的大小
     */
    private int maxSize;

    /**
     * 数组，数组模拟栈，数据就放在该数组
     */
    private int[] stack;

    /**
     * top 表示栈顶，初始化为-1
     */
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断栈满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     */
    public int pop() {
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空，没有数据~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 显示栈的情况[遍历栈]， 遍历时，需要从栈顶开始显示数据
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据~~");
            return;
        }
        //需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

}
