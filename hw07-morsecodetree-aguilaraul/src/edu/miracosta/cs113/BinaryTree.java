package edu.miracosta.cs113;

import java.io.Serializable;
import java.util.Scanner;

public class BinaryTree<E> implements Serializable {

    protected Node<E> root;

    /**
     * Constructs an empty tree
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Constructs a binary tree with the given node as the root
     * @param root makes this node the root
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructs a binary tree with the given data at the root and the two
     * given subtrees
     * @param data makes a root with this data
     * @param leftTree left tree of the root
     * @param rightTree right tree of the root
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<E>(data);
        if(leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }

        if(rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    /**
     * Returns the left subtree
     * @return the left subtree of the root
     */
    public BinaryTree<E> getLeftSubtree() {
        if(root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Returns the right subtree
     * @return the right subtree of the root
     */
    public BinaryTree<E> getRightSubtree() {
        if(root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return null;
        }
    }

    /**
     * Returns the data in the root
     * @return data in the root
     */
    public E getData() {
        return root.data;
    }

    /**
     * Returns true if this tree is a leaf, false otherwise
     * @return true if this tree is a leaf, false otherwise
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Preforms a pre-order traversal of the subtree whose root is
     * node. Appends the representation to the StringBuilder.
     * Increments the value of depth (the current tree level).
     * @param node root of subtree
     * @param depth current tree level
     * @param sb StringBuilder
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for(int i = 1; i < depth; i++) {
            sb.append(" ");
        }
        if(node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString() + "\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * Constructs a binary tree by reading its data using
     * Scanner scan
     * @param scan Scanner
     * @return a binary tree from data read
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        String data = scan.next();
        if(data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<String>(data, leftTree, rightTree);
        }
    }

    protected static class Node<E> implements Serializable {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public String toString() {
            return data.toString();
        }
    }

}
