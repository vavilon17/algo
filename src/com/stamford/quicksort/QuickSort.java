package com.stamford.quicksort;

import com.stamford.common.AlgoUtils;

/**
 * User: YaskalVV
 * Date: 18.07.13
 */
public class QuickSort {

    private long countComparisons = 0L;

    public static final void main(String[] args) {
        int[] unsortedArray = AlgoUtils.readIntArray("resources/stamford/ass2/QuickSort.txt");
    }

    public long calcComparisons(int[] array) {
        countComparisons = 0L;
        startQuickSort(array);
        return countComparisons;
    }

    public int[] startQuickSort(int[] unsortedArray) {
        return quickSortSubroutine(unsortedArray, 0, unsortedArray.length-1);
    }

    public int[] quickSortSubroutine(int[] inputArray, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(inputArray, left, right);
            quickSortSubroutine(inputArray, left, pivotIndex - 1);
            quickSortSubroutine(inputArray, pivotIndex + 1, right);
        }
        return inputArray;
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[left];
        int i = left + 1;
        for (int j = left + 1; j<=right; j++) {
            if (array[j] < pivot) {
                swap(array, j, i);
                i++;
            }
        }
        swap(array, left, i-1);
        countComparisons += right - left;
        return i-1;
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
