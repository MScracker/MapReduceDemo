package com.datamining.datastructure.rank;

import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by wanghuali1 on 2017/3/25.
 */
public class RankUtil {

	public void selectRank(int[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] > a[j]) {
					int t = a[i];
					a[i] = a[j];
					a[j] = t;
				}
			}
		}
	}

	public void bubbleRank(int[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					int t = a[j];
					a[j] = a[j + 1];
					a[j + 1] = t;
				}
			}
		}
	}

	public void selectSort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			int min = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[min] > a[j]) {
					min = j; //记下目前找到的最小值所在的位置
				}
			}
			if (i != min) {
				int t = a[i];
				a[i] = a[min];
				a[min] = t;
			}
		}
	}

	public void quickSort(int[] a, int low, int high) {
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
			//交换中间分界值与pivot
			a[low] = a[i];
			a[i] = pivot;

			quickSort(a, low, i - 1);
			quickSort(a, i + 1, high);
		}
	}

	public int[] bucketRank(int[] a) {
		int max = -1;
		for (int i = 0; i < a.length; i++) {
			if (max < a[i]) {
				max = a[i];
			}
		}
		int[] bucket = new int[max + 1];
		for (int i = 0; i < a.length; i++) {
			bucket[a[i]]++;
		}
		int[] res = new int[a.length];
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < bucket.length; j++) {
				for (int k = 0; k < bucket[j]; k++) {
					res[i] = j;
					i++;
				}
			}
		}
		return res;
	}

	public int[] repeatedRandomNumbers(int min, int max, int seed) {
		int[] a = new int[max - min + 1];
		Random random = new Random(seed);
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(max - min + 1) + min;
		}
		return a;
	}

	public int[] uniqueRandomNumbers(int min, int max) {
		int[] arr = new int[max - min + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = min + i;
		}
		shuffle(arr);
		return arr;
	}

	private void shuffle(int[] a) {
		for (int i = a.length - 1; i > 0; i--) {
			Random random = new Random();
			int j = random.nextInt(i);
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
	}

	public void insertSort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int target = a[i];
			int j = i;
			for (; j > 0 && target < a[j - 1]; j--) {
				a[j] = a[j - 1];
			}
			a[j] = target;
		}
	}


	public static void main(String[] args) {
//        QuickSort a=new QuickSort();
//        a.quicksort(0,9);
//		int[] a = {1, 10, 7, 8, 2, 4, 0, 3, 6, 5, 9};
		RankUtil rankUtil = new RankUtil();
		int[] a = rankUtil.uniqueRandomNumbers(0, 10);
//		QuickSort quickSort = new QuickSort();
//		quickSort.quickRank(a, 0, a.length - 1);
		System.out.println(Arrays.toString(a));
		rankUtil.insertSort(a);
		System.out.println(Arrays.toString(a));

	}

}
