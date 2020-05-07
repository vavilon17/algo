package com.prinston2.burrows;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.LinkedList;

public class BurrowsWheeler {

    private static class TransformResult {

        private int firstIndex;
        private String resultString;

        public TransformResult(int firstIndex, String resultString) {
            this.firstIndex = firstIndex;
            this.resultString = resultString;
        }

        public int getFirstIndex() {
            return firstIndex;
        }

        public void setFirstIndex(int firstIndex) {
            this.firstIndex = firstIndex;
        }

        public String getResultString() {
            return resultString;
        }

        public void setResultString(String resultString) {
            this.resultString = resultString;
        }

        private static TransformResult collect(String original) {
            CircularSuffixArray csa = new CircularSuffixArray(original);
            char[] chars = new char[csa.length()];
            int firstIndex = 0;
            for (int i = 0; i < csa.length(); i++) {
                int origIndex = csa.index(i);
                if (origIndex == 0) {
                    firstIndex = i;
                }
                chars[i] = original.charAt(origIndex == 0 ? csa.length() - 1 : origIndex - 1);
            }
            return new TransformResult(firstIndex, String.valueOf(chars));
        }
    }

    private static class InverseTransformer {

        private static int[] buildNextArray(String tString, char[] sortedFirstSymbols) {
            int[] nextArray = new int[sortedFirstSymbols.length];
            Map<Character, Queue<Integer>> charPositionsInT = new HashMap<>();
            for (int i = 0; i < tString.length(); i++) {
                char c = tString.charAt(i);
                if (!charPositionsInT.containsKey(c)) {
                    charPositionsInT.put(c, new LinkedList<>());
                }
                charPositionsInT.get(c).offer(i);
            }
            for (int i = 0; i < nextArray.length; i++) {
                nextArray[i] = charPositionsInT.get(sortedFirstSymbols[i]).remove();
            }
            return nextArray;
        }

        private static String reconstructString(int first, String tString) {
            char[] sortedFirstSymbols = tString.toCharArray();
            Arrays.sort(sortedFirstSymbols); // TODO consider improving performance here
            int[] next = buildNextArray(tString, sortedFirstSymbols);

            StringBuilder result = new StringBuilder();
            int k = first;
            for (int i = 0; i < sortedFirstSymbols.length; i++) {
                result.append(sortedFirstSymbols[k]);
                k = next[k];
            }
            return result.toString();
        }
    }

    private static final int BITS_FOR_INT = 32;
    private static final int BITS_FOR_STR = 8;

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        StringBuilder input = new StringBuilder();
        while (!BinaryStdIn.isEmpty()) {
            input.append(BinaryStdIn.readChar());
        }
        TransformResult result = TransformResult.collect(input.toString());
//        StdOut.println(result.getFirstIndex());
//        for (char c : result.getResultString().toCharArray()) {
//            StdOut.printf("%02x", c & 0xff);
//        }
        BinaryStdOut.write(result.getFirstIndex(), BITS_FOR_INT);
        BinaryStdOut.write(result.getResultString(), BITS_FOR_STR);
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String tString = BinaryStdIn.readString();
        String result = InverseTransformer.reconstructString(first, tString);

        BinaryStdOut.write(result);
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            throw new IllegalArgumentException();
        }
        if (args[0].trim().equals("-")) {
            transform();
        } else if (args[0].equals("+")) {
            inverseTransform();
        }
    }

}
