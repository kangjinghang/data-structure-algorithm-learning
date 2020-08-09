package com.kangjh.structure.statck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * PolandNotation2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-09
 * @since 1.0.0
 */
public class PolandNotation2 {

    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        // ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        System.out.println("中缀表达式对应的List=" + infixExpressionList);
        List<String> suffixExpreesionList = parseSuffixExpressionList(infixExpressionList);
        //ArrayList [1,2,3,+,4,*,+,5,–]
        System.out.println("后缀表达式对应的List" + suffixExpreesionList);
        System.out.printf("expression=%d", calculate(suffixExpreesionList));
    }

    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        return Arrays.asList(split);
    }

    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static List<String> parseSuffixExpressionList(List<String> ls) {
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String item : ls) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }

        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    public static List<String> toInfixExpressionList(String s) {
        // 定义一个List,存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<>();
        // 一个指针，用于遍历 中缀表达式字符串
        int i = 0;
        // 对多位数的拼接
        String str;
        // 每遍历到一个字符，就放入到c
        char c;
        do {
            //如果c是一个非数字，需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                //i需要后移
                i++;
            } else {
                //如果是一个数，需要考虑多位数
                //先将str 置成"" '0'[48]->'9'[57]
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    //拼接
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

}
