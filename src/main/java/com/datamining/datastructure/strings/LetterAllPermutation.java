package com.datamining.datastructure.strings;

/**
 * Created by wongleon on 2017/6/26.
 */
public class LetterAllPermutation {
	int num = 0;

	//在全排列中去掉重复的规则：去重的全排列就是从第一个数字起，每个数分别与它后面非重复出现的数字交换
	public static boolean canSwap(char[] chars, int begin, int end) {
		for (int i = begin; i < end; ++i) {
			if (chars[i] == chars[end]) {
				return false;
			}
		}
		return true;
	}

	//java 交换函数只能用数组
	public static void swap(char[] chars, int from, int to) {
		char temp = chars[from];
		chars[from] = chars[to];
		chars[to] = temp;
	}

	public int calcAllPermutation(char[] perm, int start, int end) {
		/**
		 * 我们使用abcd四个元素，我们分成abc整体和d整体，当递归的开始指针指向数组的最后一个位置时，即end,打印该数组，打印出abcd；
		 * 下一次递归abd为整体，c为另一部分的整体，递归到该次的尽头打印的为abdc;依次这样，能够打印出数组的全排列，
		 * 重要的是每一次递归到尽头之后，需要对数组进行还原，方便进行下一次的递归。
		 * 总结一下：全排列就是从数组第一个位置起每个元素分别与它后面的元素一一交换，交换完之后记得返回到最初的位置
		 * 在全排列中去掉重复的规则：去重的全排列就是从第一个数字起，每个数分别与它后面非重复出现的数字交换
		 */

		if (start == end) {//当只要求对数组中一个字母进行全排列时，只要就按该数组输出即可（特殊情况）
			for (int i = 0; i <= end; i++) {
				System.out.print(perm[i]);
			}
			System.out.println();
			return ++num;

		} else {//多个字母全排列（普遍情况）
			for (int i = start; i <= end; i++) {//（让指针start分别指向每一个数）
				/**
				 * 去除排列中的重复排序
				 */

				if (canSwap(perm, start, i)) {
					swap(perm, start, i);//交换数组第一个元素与后续的元素
					num = calcAllPermutation(perm, start + 1, end);//后续元素递归全排列
					swap(perm, start, i);//将交换后的数组还原
				}
			}
			return num;
		}

	}

	/**
	 * 数组元素的全组合
	 */
	static void combination(char[] chars) {
		char[] subchars = new char[chars.length]; //存储子组合数据的数组
		//全组合问题就是所有元素(记为n)中选1个元素的组合, 加上选2个元素的组合...加上选n个元素的组合的和
		for (int i = 0; i < chars.length; ++i) {
			final int m = i + 1;
			combination(chars, chars.length, m, subchars, m);
		}
	}

	/**
	 * n个元素选m个元素的组合问题的实现. 原理如下:
	 * 从后往前选取, 选定位置i后, 再在前i-1个里面选取m-1个.
	 * 如: 1, 2, 3, 4, 5 中选取3个元素.
	 * 1) 选取5后, 再在前4个里面选取2个, 而前4个里面选取2个又是一个子问题, 递归即可;
	 * 2) 如果不包含5, 直接选定4, 那么再在前3个里面选取2个, 而前三个里面选取2个又是一个子问题, 递归即可;
	 * 3) 如果也不包含4, 直接选取3, 那么再在前2个里面选取2个, 刚好只有两个.
	 * 纵向看, 1与2与3刚好是一个for循环, 初值为5, 终值为m.
	 * 横向看, 该问题为一个前i-1个中选m-1的递归.
	 * @param chars
	 * @param n 字符数组长度
	 * @param m 几个字符的组合
	 * @param subchars m个字符组合的子数组
	 * @param subn 子数组长度
	 */
	static void combination(char[] chars, int n, int m, char[] subchars, int subn) {
		if (m == 0) { //出口
			for (int i = 0; i < subn; ++i) {
				System.out.print(subchars[i]);
			}
			System.out.println();
		} else {
			for (int i = n; i >= m; --i) { // 从后往前依次选定一个
				subchars[m - 1] = chars[i - 1]; // 选定一个后
				combination(chars, i - 1, m - 1, subchars, subn); // 从前i-1个里面选取m-1个进行递归
			}
		}
	}


	public static void main(String[] args) {
		char[] s = "abcd".toCharArray();
		LetterAllPermutation lap = new LetterAllPermutation();
//		int num = lap.calcAllPermutation(s, 0, s.length - 1);
//		System.out.println(num);

		combination(s);
	}
}
