package edu.miracosta.cs113;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * PalindromeTest : a test class for isPalindrome, a method intended to utilize stacks to evaluate if a given
 * string is a palindrome.
 *
 * A palindrome is a word, phrase, number, or other sequence of characters which reads the same backwards as it does
 * forwards. Such sequences include "madam," "top spot," or "no lemon, no melon".
 */
public class PalindromeTest {

    /** True test cases which include spaces and symbols */
    private static final String[] SIMPLE_TRUE = { "", " ", "A", "7", "%", "  ", "BB", "33", "**" };
    /** False test cases which include spaces and symbols */
    private static final String[] SIMPLE_FALSE = { "AC", "71", "@+" };

    /** True test cases which include spaces */
    private static final String[] WHITE_SPACE_TRUE = { " x ", " t   t  ", " 5 5", " #      # " };
    /** False test cases which include spaces */
    private static final String[] WHITE_SPACE_FALSE = { "m   n  ", "   8  7 ", "  ^      &  "};

    /** Case-sensitive palindromes */
    private static final String[] CASE_SENSITIVE_TRUE = { "ABba", "roTOR", "rAceCaR" };

    /** Complex palindromes which include spaces, symbols, and varied capitalization */
    private static final String[] COMPLEX_TRUE = { "fOO race CAR oof", "AbBa ZaBba", "1 3 3 7  331",
                                                "N0 LEm0n, n0 Mel0n",
                                                "sT RJKLEeE R#@ $A$ @# REeEL K  JRT s" };

    /**
     * Utilizes stacks to determine if the given string is a palindrome. This method ignores whitespace and case
     * sensitivity, but does not ignore digits or symbols.
     *
     * @param s a string comprised of any character
     * @return returns true if a palindrome (ignoring whitespace and case sensitivity), false otherwise
     */
    private boolean isPalindrome(String s) {
        // TODO:
        // Implement this method body using your ArrayListStack. Be mindful of your algorithm!

        // if original string is null, throw exception
        if(s == null) {
            throw new IllegalArgumentException();
        }
        ArrayListStack left;
        ArrayListStack right;
        String test = s;

        //remove whitespaces
        test = test.replaceAll(" ", "");

        //make string lowercase
        test = test.toLowerCase();

        //if string length is 0 or 1, then it is a palindrome
        if(test.length() <= 1) {
            return true;
        }

        //Otherwise test size of string
        //if even split in half
        //if odd put middle char into third array and split remainder in half
        if(test.length() % 2 == 0) {
            left = new ArrayListStack();
            right = new ArrayListStack();
            int middleIndex = (test.length()/2)-1;

            for(int i = middleIndex; i >= 0; i--) {
                left.push(test.charAt(i));
            }
            for(int i = middleIndex+1; i < test.length(); i++) {
                right.push(test.charAt(i));
            }
        } else {
            left = new ArrayListStack();
            right = new ArrayListStack();
            ArrayListStack middle = new ArrayListStack();
            int middleIndex = test.length()/2;
            middle.push(test.charAt(middleIndex));

            for(int i = middleIndex-1; i >= 0; i--) {
                left.push(test.charAt(i));
            }
            for(int i = middleIndex+1; i < test.length(); i++) {
                right.push(test.charAt(i));
            }

        }

        //compare the two stacks
            //if true isPalindrome
            //else false !isPalindrome
        String leftHalf = "";
        String rightHalf = "";
        while(!left.empty()) {
            leftHalf = leftHalf + left.pop();
            rightHalf = rightHalf + right.pop();
        }

        if(leftHalf.compareTo(rightHalf) == 0) {
            return true;
        }
        
        return false;
    } // End of method isPalindrome

    @Test
    public void testErrors() {
        try {
            isPalindrome(null);
            fail("Checking null to see if it's a palindrome should throw IllegalArgumentException!");
        } catch (IllegalArgumentException iae) { /* Test Passed! */ }
    }

    @Test
    public void testSimpleTrueCases() {
        for (int i = 0; i < SIMPLE_TRUE.length; i ++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(SIMPLE_TRUE[i]));
        }
    }

    @Test
    public void testSimpleFalseCases() {
        for (int i = 0; i < SIMPLE_FALSE.length; i ++) {
            assertFalse((i + " This test is NOT a palindrome"), isPalindrome(SIMPLE_FALSE[i]));
        }
    }

    @Test
    public void testWhitespaceTrueCases() {
        for (int i = 0; i < WHITE_SPACE_TRUE.length; i ++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(WHITE_SPACE_TRUE[i]));
        }
    }

    @Test
    public void testWhitespaceFalseCases() {
        for (int i = 0; i < WHITE_SPACE_FALSE.length; i ++) {
            assertFalse((i + " This test is NOT a palindrome"), isPalindrome(WHITE_SPACE_FALSE[i]));
        }
    }

    @Test
    public void testCaseSensitivityCases() {
        for (int i = 0; i < CASE_SENSITIVE_TRUE.length; i ++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(CASE_SENSITIVE_TRUE[i]));
        }
    }

    @Test
    public void testComplexCases() {
        for (int i = 0; i < COMPLEX_TRUE.length; i ++) {
            assertTrue((i + " This test is a palindrome"), isPalindrome(COMPLEX_TRUE[i]));
        }
    }

} // End of class PalindromeTest