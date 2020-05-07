package com.prinston2.burrows;

public class RadixSorter {

    private static final int ASCII_TABLE_SIZE = 256;

    public static char[] sortCharArray(char[] input) {
        int[] counts = new int[ASCII_TABLE_SIZE + 1];
        for (char c : input) {
            counts[c + 1]++;
        }
        for (int r = 0; r < ASCII_TABLE_SIZE; r++) {
            counts[r + 1] += counts[r];
        }
        char[] sortedArr = new char[input.length];
        for (char c : input) {
            int idxInSorted = counts[c];
            sortedArr[idxInSorted] = c;
            counts[c]++;
        }
        return sortedArr;
    }

    public static void main(String[] args) {
        String s = "ABRACADABRA!";
        System.out.println(RadixSorter.sortCharArray(s.toCharArray()));
    }
}
