package com.stamford.inversions;

import com.stamford.common.AlgoUtils;

import java.util.Arrays;

/**
 * Created by YaskalVV on 11.07.13.
 */
public class CountInversions {

    private long inversionsCount = 0L;

    public static void main(String[] args) {
        int[] arr = AlgoUtils.readIntArray("resources/stamford/ass1/array");
        System.out.println(new CountInversions().countInversions(arr));
    }

    public long countInversions(int[] array) {
        inversionsCount = 0L;
        mergeSort(array);
        return inversionsCount;
    }

    public int[] mergeSort(int[] a) {
        if (a.length <= 1) {
            return a;
        }
        int edge = (int) Math.floor(a.length/2);
        int[] left = Arrays.copyOfRange(a, 0, edge);
        int[] right = Arrays.copyOfRange(a, edge, a.length);
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    public int[] merge (int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        for (int k = 0; k < a.length + b.length; k++) {
            if (i == a.length) {
                c[k] = b[j];
                j++;
            } else if (j == b.length) {
                c[k] = a[i];
                i++;
            } else if (a[i] < b[j]) {
                c[k] = a[i];
                i++;
            } else {
                c[k] = b[j];
                inversionsCount += a.length - i;
                j++;
            }
        }
        return c;
    }

    public static void log(Object msg) {
        System.out.println(msg);
    }
}
