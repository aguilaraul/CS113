package edu.miracosta.cs113;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MorseCodeDriver {
    private static MorseCodeTree morseCodeTree = new MorseCodeTree();
    private static final String[] SINGLES_CODE = {"*-", "-***", "-*-*",    // a, b, c
            "-**", "*", "**-*",    // d, e, f
            "--*", "****", "**",   // g, h, i
            "*---", "-*-", "*-**", // j, k, l
            "--", "-*", "---",     // m, n, o
            "*--*", "--*-", "*-*", // p, q, r
            "***", "-", "**-",     // s, t, u
            "***-", "*--", "-**-", // v, w, x
            "-*--", "--**"};

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        boolean exitMenu = false;
        System.out.println("Welcome!");
        while(!exitMenu) {
            printMenu();
            String choice = in.next();
            System.out.println(choice);

            switch (choice) {
                case "1":
                    outputTest();
                    break;
                case "2":
                    translateFromTextFile();
                    break;
                case "3":
                    System.out.println(translateFromConsole());
                    break;
                default:
                    System.out.println("Exiting...");
                    exitMenu = true;
                    break;
            }
        }
        System.exit(0);
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Please select an option");
        System.out.println("1. Output letters and their morse code");
        System.out.println("2. Translate morse code from text file");
        System.out.println("3. Translate morse code from console");
        System.out.println("4. Exit");
        System.out.println();
    }

    private static void printHeaderForOutputTest() {
        System.out.printf("%s | %s ", "Letter A-G", "Code");
        System.out.printf("%s ", "||");
        System.out.printf("%s | %s ", "Letter H-N", "Code");
        System.out.printf("%s ", "||");
        System.out.printf("%s | %s ", "Letter O-U", "Code");
        System.out.printf("%s ", "||");
        System.out.printf("%s | %s ", "Letter V-Z", "Code");
        System.out.printf("%s ", "||");
        System.out.println();
        System.out.printf("%18s", "----------------- ||");
        System.out.printf("%21s", "----------------- ||");
        System.out.printf("%21s", "----------------- ||");
        System.out.printf("%21s", "----------------- ||");
        System.out.println();
    }

    private static void outputTest() throws Exception {
        printHeaderForOutputTest();
        for(int i = 0; i < 7; i++) {
            // first column
            System.out.printf("%6s", morseCodeTree.translateFromMorseCode(SINGLES_CODE[i]));
            System.out.printf("%6s", "|");
            System.out.printf(" %-4s %s", SINGLES_CODE[i], "||");

            // second column
            System.out.printf(" %6s", morseCodeTree.translateFromMorseCode(SINGLES_CODE[i+7]));
            System.out.printf("%6s", "|");
            System.out.printf(" %-4s %s", SINGLES_CODE[i+7], "||");

            // third column
            System.out.printf(" %6s", morseCodeTree.translateFromMorseCode(SINGLES_CODE[i+14]));
            System.out.printf("%6s", "|");
            System.out.printf(" %-4s %s", SINGLES_CODE[i+14], "||");

            // fourth column
            if(i < 5) {
                System.out.printf(" %6s", morseCodeTree.translateFromMorseCode(SINGLES_CODE[i+21]));
                System.out.printf("%6s", "|");
                System.out.printf(" %-4s %s", SINGLES_CODE[i+21], "||");
            }

            System.out.println();
        }
    }

    private static void translateFromTextFile() throws Exception {
        Scanner in = new Scanner(System.in);
        // open the file
        System.out.println("Please enter the file name you'd like to analyze.");
        String fileName = in.nextLine();

        Path path = Paths.get(fileName);
        Scanner file = new Scanner(path);

        //read the file
        while(file.hasNextLine()) {
            String morseCode = file.nextLine();
            System.out.println(morseCodeTree.translateFromMorseCode(morseCode));
        }
    }

    private static String translateFromConsole() throws Exception {
        Scanner in = new Scanner(System.in);
        String morseCode = "";
        System.out.println("Please enter the morse code you would like to translate.");
        morseCode = in.nextLine();
        return morseCodeTree.translateFromMorseCode(morseCode);
    }
}
