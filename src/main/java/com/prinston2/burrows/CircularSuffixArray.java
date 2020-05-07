package com.prinston2.burrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CircularSuffixArray {

    private class CircularSuffix implements Comparable<CircularSuffix>{

        private final int headIndex;

        public CircularSuffix(int headIndex) {
            this.headIndex = headIndex;
        }

        public int getHeadIndex() {
            return headIndex;
        }

        public int getShifted(int idx) {
            int baseOnHead = idx + this.headIndex;
            return (baseOnHead >= length()) ? baseOnHead - length() : baseOnHead;
        }

        @Override
        public int compareTo(CircularSuffix o) {
            int diff = 0;
            for (int i = 0; i < length(); i++) {
                diff = orig.charAt(this.getShifted(i)) - orig.charAt(o.getShifted(i));
                if (diff != 0) {
                    break;
                }
            }
            return diff;
        }
    }

    private final String orig;
    private List<CircularSuffix> array;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        this.orig = s;
        array = new ArrayList<>(length());
        for (int i = 0; i < length(); i++) {
            array.add(i, new CircularSuffix(i));
        }
        Collections.sort(array);
    }

    // length of s
    public int length() {
        return orig.length();
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= length()) {
            throw new IllegalArgumentException();
        }
        return array.get(i).getHeadIndex();
    }

    // unit testing (required)
    public static void main(String[] args) {
        CircularSuffixArray ca = new CircularSuffixArray("ABRACADABRA!");
        for (int i = 0; i < 12; i++) {
            System.out.println(ca.index(i));
        }
    }
}
