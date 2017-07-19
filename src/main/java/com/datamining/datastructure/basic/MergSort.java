package com.datamining.datastructure.basic;

import com.datamining.datastructure.rank.RankUtil;

import java.util.Arrays;

/**
 * Created by wanghuali1 on 2017/4/8.
 */
public class MergSort {
    public int[] mergeSort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            System.out.println("low:" + low + ",mid:" + mid + ",high:" + high);
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            merge(a, low, mid, high);
        }
        return a;
    }

    private void merge(int[] a, int low, int mid, int high) {
        int[] x = new int[mid - low + 1];
        int[] y = new int[high - mid];
        System.arraycopy(a, low, x, 0, mid - low + 1);
        System.arraycopy(a, mid + 1, y, 0, high - mid);
        System.out.println("merging:" + low + "-" + mid + " and " +  (mid+1) + "-" + high);
        int i = low;
        int j = mid + 1;
        int k = 0;
        int[] temp = new int[high - low + 1];
//        System.out.print("piece:");
//        for (int m = 0; m < high - low + 1; m++) {
//            System.out.print(a[low + m] + " ");
//        }
//        System.out.println("");
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= high) {
            temp[k++] = a[j++];
        }

//        System.out.println("merge:"+Arrays.toString(temp));
        for (int l = 0; l < temp.length; l++) {
            a[low + l] = temp[l];
        }
    }

    public static void main(String[] args) {
        RankUtil rankUtil = new RankUtil();
        int[] a = rankUtil.uniqueRandomNumbers(1, 11);
        System.out.println("before:" + Arrays.toString(a));
        MergSort mergSort = new MergSort();
        mergSort.mergeSort(a, 0, a.length - 1);
        System.out.println("after:" + Arrays.toString(a));

    }
}
