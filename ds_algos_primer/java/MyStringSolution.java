/*
 *   Title: MyStringSolution
 *   Reference: https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
 *
 *   This is a basic custom implementation of a Java String. It is based off the
 *   Java 8 standard
 *
 *   Execution: javac MyStringSolution.java && java -ea MyStringSolution
 */

import java.util.*;

public final class MyStringSolution {

    // Store the string data
    final char[] data;

    // Constructor using a String
    public MyString(String s) {
        this.data = s.toCharArray();
    }

    // Return the character at index i
    public int charAt(int i) {
        if (i < 0 || i >= this.length()) throw new IndexOutOfBoundsException();
        return data[i];
    }

    // Compare this string to another string lexicographically. See Java 8
    // standard for details
    public int compareTo(MyString s) {
        // Iterate over both strings comparing characters
        for (int i = 0; i < this.length() && i < s.length(); i++) {
            if (this.charAt(i) < s.charAt(i)) return -1;
            if (this.charAt(i) > s.charAt(i)) return 1;
        }

        // If one string is a prefix of the other, the shorter string comes first
        if (this.length() < s.length()) return -1;
        if (this.length() > s.length()) return 1;
        return 0;
    }

    // Concatenate two strings
    public MyString concat(MyString s) {
        // Create space to fit both strings
        char[] newData = new char[this.length() + s.length()];

        // Insert the current string
        for (int i = 0; i < this.length(); i ++) {
            newData[i] = (char) this.charAt(i);
        }

        // Then insert the string being concatenated
        int offset = this.length();
        for (int i = 0; i < s.length(); i++) {
            newData[i + offset] = (char) s.charAt(i);
        }

        return new MyString(newData);
    }

    // Compare two strings
    public boolean equals(Object o) {
        // Standard java object comparison
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        MyString toCompare = (MyString) o;
        return this.compareTo(toCompare) == 0;
    }

    // The hash code for a String object is computed as
    //     s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
    // using int arithmetic, where s[i] is the ith character of the string, n is
    // the length of the string, and ^ indicates exponentiation.
    // (The hash value of the empty string is zero.)
    public int hashCode() {
        if (this.length() == 0) return 0;

        int hash = 0;
        for (int i = 0; i < this.length(); i++) {
            hash += this.charAt(i) * Math.pow(31, this.length()-(i+1));
        }

        return hash;
    }

    // Find the index of a substring in our string
    public int indexOf(MyString s) {
        // If s is longer than current string, can't be a substring
        if (s == null || s.length() > this.data.length) return -1;

        // Try comparing strings from each possible starting index. This is a
        // naive approach. We can use KMP for more optimal comparison
        for (int i = 0; i < this.data.length - s.length(); i++) {
            if (this.charAt(i) == s.charAt(0)) {
                int thisIdx = i;
                int sIdx = 0;
                for (int j = 0; j < s.length(); j++) {
                    if (this.charAt(thisIdx) != s.charAt(sIdx)) break;
                    thisIdx++;
                    sIdx++;
                }
                if (sIdx == s.length()) return i;
            }
        }

        return -1;
    }

    // Get the length of the string
    public int length() {
        return this.data.length;
    }

    // Split string at each occurrence of character. Returns array of all the
    // substrings in order
    public MyString[] split(char c) {
        List<MyString> result = new LinkedList<>();

        // The starting index of the current substring
        int start = 0;
        for (int i = 0; i < data.length; i++) {
            // We only have to do something if we find a split character
            if (data[i] == c) {
                // If there's a substring, add it to the result
                if (start < i) {
                    result.add(new MyString(Arrays.copyOfRange(data, start, i)));
                }
                start = i+1;
            }
        }

        // Make sure to get final substring
        if (start < data.length) {
            result.add(new MyString(Arrays.copyOfRange(data, start, data.length)));
        }

        MyString[] toReturn = new MyString[result.size()];
        result.toArray(toReturn);
        return toReturn;
    }

    // Get substring from start to end
    public MyString substring(int start, int end) {
        if (start < 0 || start >= this.length()) throw new IndexOutOfBoundsException();
        if (end < 0 || end >= this.length()) throw new IndexOutOfBoundsException();
        return new MyString(Arrays.copyOfRange(data, start, end));
    }

    // Convert to standard string
    public String toString() {
        return new String(data);
    }

    public static void main(String[] args) {
        MyString s = new MyString("abcdabcd");
        assert(s.charAt(2) == 'c');
        assert(s.compareTo(new MyString("aba")) == 1);
        assert(s.concat(new MyString("xyz")).equals(new MyString("abcdabcdxyz")));
        assert(!s.equals(new MyString("abcdabcde")));
        assert(s.hashCode() == 2147483647);
        assert(s.indexOf(new MyString("cda")) == 2);
        assert(s.length() == 8);
        assert(s.substring(2,4).equals(new MyString("cd")));

        // ADD TEST CASES HERE

        System.out.println("Passed all test cases");
    }

}
