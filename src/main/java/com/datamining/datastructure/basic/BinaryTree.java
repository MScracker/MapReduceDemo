package com.datamining.datastructure.basic;


/**
 * Created by wanghuali1 on 2017/4/15.
 */
public class BinaryTree {
    /**
     * 维护构建二叉树的值和值索引
     */
    public static class TreeValue {
        public static int index = 0;
        public static final int[] TREE_VALUE = new int[]{1, 2, 3, 0, 4, 5, 0, 0, 6, 0, 0, 7, 0, 0, 8, 0, 9, 10, 0, 0, 0};
    }

    /**
     * Create A Binary Tree with TreeValue
     *
     * @node: a tree node
     * @i: the index of tree value
     */
    public static TreeNode createTree(TreeNode node, int i) {
        if (0 == TreeValue.TREE_VALUE[i]) {
            return null;
        } else {
            node.setVal(TreeValue.TREE_VALUE[i]);
        }

        TreeNode leftChild = new TreeNode();
        node.left = createTree(leftChild, ++TreeValue.index);
        TreeNode rightChild = new TreeNode();
        node.right = createTree(rightChild, ++TreeValue.index);

        return node;
    }

    /**
     * Definition for binary tree
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
            left = null;
            right = null;
        }

        TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
        }

        public void setVal(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode{\n" +
                    "\tval=" + val +
                    ",\n\tleft=" + left +
                    ",\n\tright=" + right +
                    "\n}";
        }
    }

    public static String print(TreeNode root) {
        if (root == null) {
            return "null";
        }
        String info = "TreeNode{\n" +
                        "\tval=" + root.val +
                        ",\n\tleft=" + print(root.left) +
                        ",\n\tright=" + print(root.right) +
                        "\n}";
        System.out.println(info);
        return info;
    }

    public static void main(String[] args) {
        int i= 0xFFFFFFFF;
        int j = i >> 1;
        System.out.println(j);
//        TreeNode root = new TreeNode();
//        root = createTree(root, index);
////        System.out.println(root.toString());
//        print(root);
    }
}
