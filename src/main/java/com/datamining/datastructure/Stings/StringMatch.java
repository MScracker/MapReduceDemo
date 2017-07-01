package com.datamining.datastructure.Stings;

/**
 * Created by wongleon on 2017/6/26.
 */
public class StringMatch {

    public static Boolean stringContains(char[] longstr, char[] shortstr) {
        int hash = 0;
        for (int i = 0; i < longstr.length; i++) {
            hash |= 1 << (longstr[i] - 'a');
        }
        int code = 0;
        for (int i = 0; i < shortstr.length; i++) {
            code |= 1 << (shortstr[i] - 'a');
        }
        if ((hash & code) != 0) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        char[] l = "dhdfsgdf".toCharArray();
        char[] s = "a".toCharArray();
        System.out.printf(stringContains(l, s).toString());
    }
}
