package edu.miracosta.cs113;

public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {

    /** Flag to indicate that height of tree has increase */
    private boolean increase;

    @Override
    public boolean add(E item) {
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }

    private AVLNode<E> add(AVLNode<E> localRoot, E item) {
        // if localRoot is null then return a new AVLNode with the item inserted
        // and set addReturn and increase to true
        if(localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode<E>(item);
        }

        if(item.compareTo(localRoot.data) == 0) {
            // Item is already in the tress
            increase = false;
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            localRoot.left = add((AVLNode<E>) localRoot.left, item);
            if(increase) {
                decrementBalance(localRoot);
                if(localRoot.balance == AVLNode.BALANCED) {
                    increase = false;
                }
                if(localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot; // Rebalance not needed
        } else {
            localRoot.right = add((AVLNode<E>) localRoot.right, item);
            if(increase) {
                incrementBalance(localRoot);
                if(localRoot.balance == AVLNode.BALANCED) {
                    increase = false;
                }
                if(localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    increase = false;
                    return rebalanceRight(localRoot);
                }
            }
            return localRoot; // Rebalance not needed
        }
    }

    /** Method to rebalance left
     * pre: localRoot is the root of an AVL subtree that is critically left-heavy
     * post: Balance is restored
     * @param localRoot Root of the AVL subtree that needs rebalancing
     * @return a new localRoot
     */
    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
        // Obtain a reference to left child
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;

        // See whether Left-Right heavy
        if(leftChild.balance > AVLNode.BALANCED) {
            // Obtain reference to left-right child
            AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;

            /* Adjust the balances to their new values after the rotation */
            if(leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            } else if (leftRightChild.balance > AVLNode.BALANCED) {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else {
                leftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            localRoot.left = rotateLeft(leftChild);
        } else { // Left-Left Case
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Rotate the local root right
        return (AVLNode<E>) rotateRight(localRoot);
    }

    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
        // Obtain a reference to right child
        AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;

        // See whether Right-Left heavy
        if(rightChild.balance < AVLNode.BALANCED) {
            // Obtain reference to left-right child
            AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;

            /* Adjust the balances to their new values after the rotation */
            if(rightLeftChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            } else if (rightLeftChild.balance < AVLNode.BALANCED) {
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else {
                rightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            localRoot.right = rotateRight(rightChild);
        } else { // Right-Right Case
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Rotate the local root left
        return (AVLNode<E>) rotateLeft(localRoot);
    }

    /**
     * Decrements balance and resets increase to false if the balance changes
     * from right-heavy to balanced
     * @param node Node's balance
     */
    private void decrementBalance(AVLNode<E> node) {
        // Decrement the balance
         node.balance--;
         if(node.balance == AVLNode.BALANCED) {
             /* If now balanced, overall height has not increase */
             increase = false;
         }
    }

    /**
     * Increments balance and resets increase to false if the balance changes
     * from left-heavy to balanced
     * @param node Node's balance
     */
    private void incrementBalance(AVLNode<E> node) {
        // Decrement the balance
        node.balance++;
        if(node.balance == AVLNode.BALANCED) {
            /* If now balanced, overall height has not increase */
            increase = false;
        }
    }

    private static class AVLNode<E> extends Node<E> {
        public static final int LEFT_HEAVY = -1;
        public static final int BALANCED = 0;
        public static final int RIGHT_HEAVY = 1;

        /** Balance is right subtree height - left subtree height */
        private int balance;

        /** Constructs a node with given item as the data field
         * @param item The data field
         */
        public AVLNode(E item) {
            super(item);
            balance = BALANCED;
        }

        /** Return a string representation of this object
         *  The balance value is appended to the contents
         * @return String representation of this object
         */
        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }
    }
}
