/*
 *   Title: MyString
 *   Reference: https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
 *
 *   This is a basic custom implementation of a Java String. It is based off the
 *   Java 8 standard
 *
 *   Execution: javac MyString.java && java -ea MyString
 */

import java.util.*;

public final class MyString{

    // Store the string data
    final char[] data;

    // Constructor using a String
    public MyString(String s) {
        // IMPLEMENT
    }

    // Return the character at index i
    public int charAt(int i) {
        // IMPLEMENT
    }

    // Compare this string to another string lexicographically. See Java 8
    // standard for details
    public int compareTo(MyString s) {
        // IMPLEMENT
    }

    // Concatenate two strings
    public MyString concat(MyString s) {
        // IMPLEMENT
    }

    // Compare two strings
    public boolean equals(Object o) {
        // IMPLEMENT
    }

    // The hash code for a String object is computed as
    //     s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
    // using int arithmetic, where s[i] is the ith character of the string, n is
    // the length of the string, and ^ indicates exponentiation.
    // (The hash value of the empty string is zero.)
    public int hashCode() {
        // IMPLEMENT
    }

    // Find the index of a substring in our string. You can implement a naive
    // approach here. No need to use KMP Algorithm
    public int indexOf(MyString s) {
        // IMPLEMENT
    }

    // Get the length of the string
    public int length() {
        // IMPLEMENT
    }

    // Split string at each occurrence of character. Returns array of all the
    // substrings in order
    public MyString[] split(char c) {
        // IMPLEMENT
    }

    // Get substring from start to end
    public MyString substring(int start, int end) {
        // IMPLEMENT
    }

    // Convert to standard string
    public String toString() {
        // IMPLEMENT
    }

    public static void main(String[] args) {
        // ADD TEST CASES HERE
    }

}
