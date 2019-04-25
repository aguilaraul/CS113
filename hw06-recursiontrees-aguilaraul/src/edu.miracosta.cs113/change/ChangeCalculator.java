package edu.miracosta.cs113.change;

import java.io.*;

/**
 * ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
 * possible coin combinations representing a given monetary value in cents.
 *
 * Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
 * Ch. 5, Programming Project #7, pg. 291.
 *
 * NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
 * verify that all given coin combinations are unique.
 */
public class ChangeCalculator {
    static String fileName = "src/edu.miracosta.cs113/change/CoinCombinations.txt";
    static int[] coins = {25, 10, 5, 1};
    static int index = 0;

    /**
     * Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
     * equal the given monetary value in cents.
     *
     * In addition to returning the number of unique combinations, this method will print out each combination to the
     * console. The format of naming each combination is up to the user, as long as they adhere to the expectation
     * that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
     * include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
     *
     * @param cents a monetary value in cents
     * @return the total number of unique combinations of coins of which the given value is comprised
     */
    public static int calculateChange(int cents) {
        return calculateChange(cents, coins, index);
    }

    private static int calculateChange(int cents, int[] coins, int index) {
        // TODO:
        // Implement a recursive solution following the given documentation.
        int[] coinCombos = {0, 0, 0, 0};
        int combos = 0;

        if(cents == 0) {
            return 1;
        }

        if(cents < 0) {
            return 0;
        }

        if(index >= coins.length) {
            return 0;
        }


        for(int i = index; i < coins.length; i++) {
            combos += calculateChange(cents - coins[i], coins, i);
            coinCombos[i] += 1;
        }

        //printCombosToConsole(coinCombos);
        return combos;
    }

    public static void printCombosToConsole(int[] combos) {
        System.out.print("[");
        for(int i = 0; i < 4; i++) {
            System.out.print(combos[i]);
            if(i < 3) {
                System.out.print(", ");
            }
        }
        System.out.print("]\n");
    }

    /**
     * Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
     * nickels, and pennies that equal the given value in cents.
     *
     * Similar to calculateChange's function in printing each combination to the console, this method will also
     * produce a text file named "CoinCombinations.txt", writing each combination to separate lines.
     *
     * @param cents a monetary value in cents
     */
    public static void printCombinationsToFile(int cents) {
        // TODO:
        // This when calculateChange is complete. Note that the text file must be created within this directory.
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName);
            writer.print("[");
            for(int i = 0; i < 4; i++) {
                writer.print("" + coinCombos[i]);
                if(i < 3) {
                    writer.print(", ");
                }
            }
            writer.println("]");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

} // End of class ChangeCalculator