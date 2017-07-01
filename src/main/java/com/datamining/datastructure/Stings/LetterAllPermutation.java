package com.datamining.datastructure.Stings;

/**
 * Created by wongleon on 2017/6/26.
 */
public class LetterAllPermutation {

    public static void calcAllPermutation(char[] perm, int from, int to) {
        if (to <= 1) {
            return;
        }

        if (from == to) {
            for (int i = 0; i <= to; i++) {
                System.out.print(perm[i]);
            }
            System.out.println("");
        } else {
            for (int j = from; j <= to; j++) {
                char t = perm[j];
                perm[j] = perm[from];
                perm[from] = t;
                calcAllPermutation(perm, from + 1, to);
                char r = perm[j];
                perm[j] = perm[from];
                perm[from] = r;
            }
        }
    }


    public static void main(String[] args) {
        char[] s = "abc".toCharArray();
        calcAllPermutation(s, 0, s.length - 1);
    }
}
