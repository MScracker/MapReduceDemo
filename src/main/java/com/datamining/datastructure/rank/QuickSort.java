package com.datamining.datastructure.rank;

/**
 * Created by wanghuali1 on 2017/3/26.
 */
public class QuickSort {
    private int[] a = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};

    public void quicksort(int left, int right) {
        int i, j, t, temp;
        if (left > right)
            return;

        temp = a[left]; //temp中存的就是基准数
        i = left;
        j = right;
        while (i != j) {
            //顺序很重要，要先从右边开始找
            while (a[j] >= temp && i < j)
                j--;
            //再找右边的
            while (a[i] <= temp && i < j)
                i++;
            //交换两个数在数组中的位置
            if (i < j) {
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        //最终将基准数归位
        a[left] = a[i];
        a[i] = temp;

        quicksort(left, i - 1);//继续处理左边的，这里是一个递归的过程
        quicksort(i + 1, right);//继续处理右边的 ，这里是一个递归的过程
    }


    public int[] quickRank(int[] a, int low, int high) {
        int i = low;
        int j = high;
        if (low < high) {
            int pivot = a[low];
            while (i != j) {
                while (i < j && a[j] >= pivot) {
                    j--;
                }
                while (i < j && a[i] <= pivot) {
                    i++;
                }
                if (i < j) {
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
            a[low] = a[i];
            a[i] = pivot;

            quickRank(a, low, i - 1);
            quickRank(a, i + 1, high);
        }
        return a;
    }


    public static void main(String[] args) {
//        QuickSort a=new QuickSort();
//        a.quicksort(0,9);
//        int[] a = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        RankUtil rankUtil = new RankUtil();
        int[] a = rankUtil.uniqueRandomNumbers(0, 1000);
        QuickSort quickSort = new QuickSort();
        quickSort.quickRank(a, 0, a.length-1);
//        System.out.println(Arrays.toString(a));

    }
}
