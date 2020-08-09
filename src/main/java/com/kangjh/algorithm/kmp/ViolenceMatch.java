package com.kangjh.algorithm.kmp;

/**
 * 暴力匹配算法
 *
 * @author kangjinghang
 * @version 1.0
 * @since 2019-08-07
 */
public class ViolenceMatch {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int index = violenceMatch(str1, str2);
        System.out.println("index=" + index);
    }

    /**
     * 暴力匹配算法实现
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        // i 索引指向s1
        int i = 0;
        // j 索引指向s2
        int j = 0;

        // 保证匹配时，不越界
        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) {
                // 匹配成功
                i++;
                j++;
            } else {
                // 没有匹配成功
                // 如果失配（即str1[i]! = str2[j]），令i = i - (j - 1)，j = 0。
                i = i - (j - 1);
                j = 0;
            }
        }

        //判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
