package edu.miracosta.cs113;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class HuffmanTree implements HuffmanInterface {

    /**
     * Reference to the completed Huffman tree
     */
    private BinaryTree<HuffData> huffTree;

    /**
     * Huffman tree constructor
     * @param s builds tree based off String
     */
    public HuffmanTree(String s) {
        int[] asciiArray = new int[128];
        HuffData[] symbols = countLetterFrequency(s, asciiArray);
        buildTree(symbols);
    }

    private HuffData[] countLetterFrequency(String message, int[] asciiArray) {
        int numOfSymbols = 0;

        System.out.println(message);
        //message = "Hello World!!! Ready for Spring 2019?";

        char[] charArray = message.toCharArray();
        for(char aChar:charArray) {
            // tab or new line
            if(aChar == 9 || aChar == 10) {
                if(asciiArray[(int) aChar] < 1) {
                    numOfSymbols++;
                }
                asciiArray[(int) aChar]++;
            }
            // space or exclamation mark
            if(aChar == 32 || aChar == 33) {
                if(asciiArray[(int) aChar] < 1) {
                    numOfSymbols++;
                }
                asciiArray[(int) aChar]++;
            }
            // period
            if(aChar == 46) {
                if(asciiArray[(int) aChar] < 1) {
                    numOfSymbols++;
                }
                asciiArray[(int) aChar]++;
            }
            // numbers
            if(aChar > 47 && aChar < 58) {
                if(asciiArray[(int) aChar] < 1) {
                    numOfSymbols++;
                }
                asciiArray[(int) aChar]++;
            }
            // question mark
            if(aChar == 63) {
                if(asciiArray[(int) aChar] < 1) {
                    numOfSymbols++;
                }
                asciiArray[(int) aChar]++;
            }
            // upper case letters
            if(aChar > 64 && aChar < 91) {
                if(asciiArray[(int) aChar] < 1) {
                    numOfSymbols++;
                }
                asciiArray[(int) aChar]++;
            }
            // lower case letters
            if(aChar > 96 && aChar < 123) {
                if(asciiArray[(int) aChar] < 1) {
                    numOfSymbols++;
                }
                asciiArray[(int) aChar]++;
            }
        }

        HuffData[] symbols = new HuffData[numOfSymbols];
        int j = 0;
        for(int i = 0; i < asciiArray.length; i++) {
            if(asciiArray[i] > 0) {
                System.out.printf("%d %c %d\n", i, (char) i, asciiArray[i]);
                symbols[j++] = new HuffData(asciiArray[i], (char) i);
            }
        }

        for(HuffData b:symbols) {
            System.out.println("Weight: " + b.weight + "\tSymbol: " + b.symbol);
        }
        return symbols;
    }

    @Override
    public String decode(String codedMessage) {
        StringBuilder result = new StringBuilder();
        BinaryTree<HuffData> currentTree = huffTree;
        for(int i = 0; i < codedMessage.length(); i++) {
            if(codedMessage.charAt(i) == '1') {
                currentTree = currentTree.getRightSubtree();
            } else {
                currentTree = currentTree.getLeftSubtree();
            }
            
            if(currentTree.isLeaf()) {
                HuffData theData = currentTree.getData();
                result.append(theData.symbol);
                currentTree = huffTree;
            }
        }
        
        return result.toString();
    }

    @Override
    public String encode(String message) {

        return "";
    }

    /**
     * Builds the Huffman Tree using the given alphabet and weights
     * post: huffTree contains a reference to the Huffman tree
     * @param symbols An array of HuffData objects
     */
    public void buildTree(HuffData[] symbols) {
        Queue<BinaryTree<HuffData>> theQueue =
                new PriorityQueue<>(symbols.length, new CompareHuffmanTrees());
        
        // Load the queue with the leaves
        for(HuffData nextSymbol:symbols) {
            BinaryTree<HuffData> aBinaryTree = new BinaryTree<>(nextSymbol, null, null);
            theQueue.offer(aBinaryTree);
        }
        
        // Build the tree
        while (theQueue.size() > 1) {
            BinaryTree<HuffData> left = theQueue.poll();
            BinaryTree<HuffData> right = theQueue.poll();
            
            double wl = left.getData().weight;
            double wr = right.getData().weight;
            
            HuffData sum = new HuffData(wl + wr, null);
            BinaryTree<HuffData> newTree = new BinaryTree<>(sum, left, right);
            theQueue.offer(newTree);
        }
        
        // The queue should now contain only one item
        huffTree = theQueue.poll();
    }

    /**
     * Outputs the resulting code
     * @param out A PrintStream to write the output to
     * @param code The code up to this node
     * @param tree The current node in the tree
     */
    private void printCode(PrintStream out, String code, BinaryTree<HuffData> tree) {
        HuffData theData = tree.getData();
        if(theData.symbol != null) {
            if(theData.symbol.equals(" ")) {
                out.println("space: " + code);
            } else {
                out.println(theData.symbol + ": " + code);
            }
        } else {
            printCode(out, code + "0", tree.getLeftSubtree());
            printCode(out, code + "1", tree.getRightSubtree());
        }
    }
    
    static class HuffData implements Serializable {
        private double weight;
        private Character symbol;
        
        public HuffData(double weight, Character symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }
    }

    /**
     * A Comparator for Huffman trees
     */
    private static class CompareHuffmanTrees implements Comparator<BinaryTree<HuffData>> {

        /**
         * Compare two objects
         * @param leftTree The left-hand object
         * @param rightTree The right-hand object
         * @return -1 if left less than right
         *          0 if left equals right
         *         +1 if left greater than right
         */
        public int compare(BinaryTree<HuffData> leftTree, BinaryTree<HuffData> rightTree) {
            double wLeft = leftTree.getData().weight;
            double wRight = rightTree.getData().weight;
            return Double.compare(wLeft, wRight);
        }
    }
}
