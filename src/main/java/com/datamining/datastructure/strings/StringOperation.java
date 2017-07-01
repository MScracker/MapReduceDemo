package com.datamining.datastructure.strings;

/**
 * Created by wongleon on 2017/6/25.
 */
public class StringOperation {
    //首部若干字母移至尾部：
    // 法一：
    public static void reverse(char[] s, int from, int to) {
        while (from < to) {
            char t = s[from];
            s[from++] = s[to];
            s[to--] = t;
        }
    }

    public static void leftRotateString(char[] s, int numOfString) {
        numOfString %= s.length;
        reverse(s, 0, numOfString - 1);
        reverse(s, numOfString, s.length - 1);
        reverse(s, 0, s.length - 1);
    }

    //法二：
    public static void leftShiftOne(char[] s){
        char t = s[0];
        for (int i = 1; i < s.length; i++) {
            s[i - 1 ] = s[i];
        }
        s[s.length - 1] = t;
    }

    public static  void leftRotateLoop(char[] s, int numOfString){
        while (numOfString-- > 0){
            leftShiftOne(s);
        }
    }


    public static void main(String[] args) {
        char[] s = "abcdef".toCharArray();
        reverse(s, 0, s.length - 1);
//        leftRotateString(s, 2);
//        leftRotateLoop(s,2);
        System.out.println(s);
    }

}
