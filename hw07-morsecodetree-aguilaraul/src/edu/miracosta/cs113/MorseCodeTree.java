package edu.miracosta.cs113;

import edu.miracosta.cs113.exceptions.InvalidMorseCodeException;

import java.io.FileReader;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

    private BinaryTree<String> mct;

    public BinaryTree<String> readMorseCodeTree() throws Exception {
        String[] letters = new String[27];

        String fileName = "src/edu/miracosta/cs113/docs/MorseCodeTree.txt";
        Scanner file;

        file = new Scanner(new FileReader(fileName));

        int i = 0;
        while (file.hasNextLine()) {
            String line = file.nextLine();
            String[] parse = line.split(" ");
            letters[i] = parse[0];
            i++;
        }

        // Right side (-)
        // 5th level
        Node<String> z = new Node<>(letters[25]);
        Node<String> q = new Node<>(letters[26]);
        // 4th level
        BinaryTree<String> gzq = new BinaryTree<String>(letters[13], new BinaryTree<>(z), new BinaryTree<>(q));

        Node<String> c = new Node<>(letters[23]);
        Node<String> y = new Node<>(letters[24]);
        BinaryTree<String> kcy = new BinaryTree<String>(letters[12], new BinaryTree<>(c), new BinaryTree<>(y));

        Node<String> b = new Node<>(letters[21]);
        Node<String> x = new Node<>(letters[22]);
        BinaryTree<String> dbx = new BinaryTree<String>(letters[11], new BinaryTree<>(b), new BinaryTree<>(x));

        // 3rd level
        BinaryTree<String> ndk = new BinaryTree<>(letters[5], dbx, kcy);

        Node<String> o = new Node<>(letters[14]);
        BinaryTree<String> mgo = new BinaryTree<>(letters[6], gzq, new BinaryTree<>(o));

        // 2nd level
        BinaryTree<String> tnm = new BinaryTree<>(letters[2], ndk, mgo);

        // Left side (*)
        Node<String> p = new Node<>(letters[19]);
        Node<String> j = new Node<>(letters[20]);
        BinaryTree<String> wpj = new BinaryTree<String>(letters[10], new BinaryTree<>(p), new BinaryTree<>(j));

        Node<String> l = new Node<>(letters[18]);
        BinaryTree<String> rl = new BinaryTree<String>(letters[9], new BinaryTree<>(l), null);

        BinaryTree<String> arw = new BinaryTree<String>(letters[4], rl, wpj);

        Node<String> f = new Node<>(letters[17]);
        BinaryTree<String> uf = new BinaryTree<String>(letters[8], new BinaryTree<>(f), null);

        Node<String> h = new Node<>(letters[15]);
        Node<String> v = new Node<>(letters[16]);
        BinaryTree<String> shv = new BinaryTree<String>(letters[7], new BinaryTree<>(h), new BinaryTree<>(v));

        BinaryTree<String> isu = new BinaryTree<String>(letters[3], shv, uf);

        BinaryTree<String> eia = new BinaryTree<String>(letters[1], isu, arw);

        // Root
        return new BinaryTree<String>(letters[0], eia, tnm);
    }

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) throws Exception {
        StringBuilder sb = new StringBuilder();
        BinaryTree<String> mct = readMorseCodeTree();
        BinaryTree<String> rootNode = mct;

        String[] parse = morseCode.split(" ");

        for(String letter:parse) {
            rootNode = mct;
            for(int i = 0; i < letter.length(); i++) {
                if(letter.charAt(i) == '*') {
                    rootNode = rootNode.getLeftSubtree();
                } else if (letter.charAt(i) == '-') {
                    rootNode = rootNode.getRightSubtree();
                } else {
                    throw new InvalidMorseCodeException("Invalid morse code.");
                }
            }
            sb.append(rootNode.getData());
        }

        String message = sb.toString();
        return message;
    }

} // End of class MorseCodeTree