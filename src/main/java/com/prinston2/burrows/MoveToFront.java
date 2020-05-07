package com.prinston2.burrows;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    private static final int ASCII_TABLE_SIZE = 256;
    private static final int BITS_FOR_STR = 8;

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        int[] charToIndexMapping = initCharToIndexMapping();

        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            int currentSymbolIdx = charToIndexMapping[c];
//            StdOut.printf("%02x", currentSymbolIdx & 0xff);
            moveToFront(c, charToIndexMapping, currentSymbolIdx);
            BinaryStdOut.write(currentSymbolIdx, BITS_FOR_STR);
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        int[] charToIndexMapping = initCharToIndexMapping();

        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            int decodedChar = indexByValue(charToIndexMapping, c);
            moveToFront(decodedChar, charToIndexMapping, c);
            BinaryStdOut.write(decodedChar, BITS_FOR_STR);
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            throw new IllegalArgumentException();
        }
        if (args[0].trim().equals("-")) {
            encode();
        } else if (args[0].equals("+")) {
            decode();
        }
    }

    // Index of this array is ASCII codes of symbols. Values - shifted indices of them
    private static int[] initCharToIndexMapping() {
        int[] arr = new int[ASCII_TABLE_SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private static void moveToFront(int c, int[] arr, int prevIdx) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < prevIdx) {
                arr[i]++;
            }
        }
        arr[c] = 0;
    }

    private static int indexByValue(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }

}
