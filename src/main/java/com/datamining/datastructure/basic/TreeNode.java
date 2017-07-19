package com.datamining.datastructure.basic;


/**
 * Created by wanghuali1 on 2017/3/22.
 */

public class TreeNode<T> {
	private TreeNode left;
	private TreeNode right;
	private T value;

	public static class Leaf<T> extends TreeNode {
		public Leaf(T value) {
			super(value, null, null);
		}
	}

	public TreeNode(T value, TreeNode left, TreeNode right) {
		this.left = left;
		this.right = right;
		this.value = value;
	}

	private static void visit(TreeNode n) {
		System.out.print(n.value + " ");
	}

	//中序遍历
	public static void inorder(TreeNode n) {
		if (n == null) {
			return;
		}
		inorder(n.left);
		visit(n);
		inorder(n.right);
	}

	public static void main(String[] args) {
		TreeNode n = new TreeNode(1.0, new TreeNode(2.0,
				new Leaf(3.0),
				new Leaf(4.0)
		),
				new TreeNode(5.0,
						null,
						new Leaf(6.0))
		);

		inorder(n);
	}
}

