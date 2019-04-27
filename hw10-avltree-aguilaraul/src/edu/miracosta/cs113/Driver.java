package edu.miracosta.cs113;

public class Driver {

    public static void main(String[] args) {
        int size = 12;
        AVLTree avlTree = new AVLTree();
        BinarySearchTree bsTree = new BinarySearchTree();

        int[] nums = new int[size];
//        for(int i = 1; i < 6; i++) {

//        }

        String[] str = "The quick brown fox jumps over the lazy dog".split(" ");
        String[] str2 = "Now is the time for all good men to come to the aid of the party".split(" ");


//        for(int n:nums) {
//            avlTree.add(n);
//            bsTree.add(n);
//        }


        avlTree.add(50);
        bsTree.add(50);
        System.out.println("Binary Tree:");
        System.out.println(bsTree.toString2());
        System.out.println("AVL Tree:");
        System.out.println(avlTree.toString2());
        avlTree.add(25);
        bsTree.add(25);
        System.out.println("Binary Tree:");
        System.out.println(bsTree.toString2());
        System.out.println("AVL Tree:");
        System.out.println(avlTree.toString2());
        avlTree.add(30);
        bsTree.add(30);
        System.out.println("Binary Tree:");
        System.out.println(bsTree.toString2());
        System.out.println("AVL Tree:");
        System.out.println(avlTree.toString2());
        avlTree.add(27);
        bsTree.add(27);
        System.out.println("Binary Tree:");
        System.out.println(bsTree.toString2());
        System.out.println("AVL Tree:");
        System.out.println(avlTree.toString2());
        avlTree.add(40);
        bsTree.add(40);
        System.out.println("Binary Tree:");
        System.out.println(bsTree.toString2());
        System.out.println("AVL Tree:");
        System.out.println(avlTree.toString2());
        avlTree.add(35);
        bsTree.add(35);
        System.out.println("Binary Tree:");
        System.out.println(bsTree.toString2());
        System.out.println("AVL Tree:");
        System.out.println(avlTree.toString2());
    }

}
