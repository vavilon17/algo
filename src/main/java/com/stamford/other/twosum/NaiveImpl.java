package com.stamford.other.twosum;

import com.stamford.common.AlgoUtils;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 18.08.13
 */
public class NaiveImpl {

    public static final void main(String[] args) {
        String fileName = "resources/stamford/ass6/algo1_programming_prob_2sum.txt";
        long[] arr = AlgoUtils.readLongArray(fileName);
        System.out.println("Result of naive subroutine = " + new NaiveImpl().calc(arr));
    }

    public int calc(long[] arr) {
        long[] sortedArray = arr.clone();
        Arrays.sort(sortedArray);
        int count = 0;
        int contrElemPos;
        for (long t = -10000L; t <= 10000L; t++) {
            System.out.println(t);
            for (long elem : arr) {
                if (t - elem != elem) {
                    contrElemPos = Arrays.binarySearch(sortedArray, t - elem);
                    if (contrElemPos >= 0) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }
}