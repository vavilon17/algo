package com.stamford.other.twosum;

import com.stamford.common.AlgoUtils;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 17.08.13
 */
public class TwoSum {

    private int sumLowBound;
    private int sumUpperBound;

    public TwoSum(int sumLowBound, int sumUpperBound) {
        this.sumLowBound = sumLowBound;
        this.sumUpperBound = sumUpperBound;
    }

    public static final void main(String[] args) {
        String fileName = "resources/stamford/ass6/algo1_programming_prob_2sum.txt";
        TwoSum program = new TwoSum(-10000, 10000);
        System.out.print("Result = " + program.calc(AlgoUtils.readUniqueLongArray(fileName)));
    }

    public int calc(long[] array) {
        HashSet<Long> set = fillHashTable(array);
        int count = 0;
        boolean exists;
        for (int t = sumLowBound; t <= sumUpperBound; t++) {
            System.out.println(t);
            exists = false;
            for (long item : array) {
                if (set.contains(t - item) && item != t - item) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    private HashSet<Long> fillHashTable(long[] array) {
        HashSet<Long> set = new HashSet<Long>(array.length);
        for (long item : array) {
            set.add(item);
        }
        return set;
    }
}
