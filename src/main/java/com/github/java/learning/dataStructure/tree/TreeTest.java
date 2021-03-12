package com.github.java.learning.dataStructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * created by guanjian on 2021/3/4 16:29
 */
public class TreeTest {

    /**
     * 声明树
     */
    static class TreeNode {
        /**
         * 节点值
         */
        String value;
        /**
         * 左子树
         */
        TreeNode leftChild;
        /**
         * 右子树
         */
        TreeNode rightChild;

        public TreeNode(String value) {
            this.value = value;
        }

        public TreeNode(String value, TreeNode leftChild, TreeNode rightChild) {
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    /**
     * 前序遍历（递归实现）
     *
     * @param root
     * @return
     */
    public static void preorderTraveral(TreeNode root) {
        if (null != root) {
            System.out.format(" %s -> ", root.value);
            preorderTraveral(root.leftChild);
            preorderTraveral(root.rightChild);
        }
    }

    /**
     * 前序遍历（栈实现）
     *
     * @param root
     * @return
     */
    public static void preorderTraveralByStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack();

        TreeNode curNode = root;
        while (null != curNode || !stack.empty()) {
            if (null != curNode) {
                System.out.format(" %s -> ", curNode.value);
                stack.push(curNode);
                curNode = curNode.leftChild;
            } else {
                curNode = stack.pop().rightChild;
            }
        }
    }

    /**
     * 后序遍历(递归实现)
     *
     * @param root
     * @return
     */
    public static void postorderTraveral(TreeNode root) {
        if (null != root) {
            postorderTraveral(root.leftChild);
            postorderTraveral(root.rightChild);
            System.out.format(" %s -> ", root.value);
        }
    }

    /**
     * 中序遍历(递归实现)
     *
     * @param root
     * @return
     */
    public static void inorderTraveral(TreeNode root) {
        if (null != root) {
            inorderTraveral(root.leftChild);
            System.out.format(" %s -> ", root.value);
            inorderTraveral(root.rightChild);
        }
    }

    /**
     * 层级遍历(队列实现)
     *
     * @param root
     * @return
     */
    public static void levelTraveral(TreeNode root) {
        if (null == root) return;

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        while (!queue.isEmpty()){
            TreeNode curNode = queue.poll();
            System.out.format(" %s -> ", curNode.value);

            if (curNode.leftChild != null) {
                queue.add(curNode.leftChild);
            }
            if (curNode.rightChild != null) {
                queue.add(curNode.rightChild);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode("A");
        root.leftChild = new TreeNode("B");
        root.rightChild = new TreeNode("C");

        root.leftChild.leftChild = new TreeNode("D");
        root.leftChild.rightChild = new TreeNode("E");

        root.rightChild.leftChild = new TreeNode("F");
        root.rightChild.rightChild = new TreeNode("G");

        //前序遍历 根、左、右
        preorderTraveral(root);
        System.out.println("\n");
        preorderTraveralByStack(root);
        System.out.println("\n");

        //后序遍历 左、右、根
        postorderTraveral(root);
        System.out.println("\n");

        //中序遍历 左、根、右
        inorderTraveral(root);
        System.out.println("\n");

        //层次遍历
        levelTraveral(root);
        System.out.println("\n");
    }
}
