package com.stamford.quicksort;

import com.stamford.common.AlgoUtils;

/**
 * User: YaskalVV
 * Date: 18.07.13
 */
public class QuickSort {

    public enum PivotChoosingCase {
        FIRST, MEDIAN_OF_THREE, LAST
    }

    private PivotChoosingCase mode = PivotChoosingCase.MEDIAN_OF_THREE;
    private long countComparisons = 0L;

    public static final void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] unsortedArray = AlgoUtils.readIntArray("resources/stamford/ass2/QuickSort.txt");
        System.out.println("First case: " + quickSort.calcComparisons(unsortedArray.clone(), PivotChoosingCase.FIRST));
        System.out.println("Second case: " + quickSort.calcComparisons(unsortedArray.clone(), PivotChoosingCase.LAST));
        System.out.println("Third case: " + quickSort.calcComparisons(unsortedArray.clone(), PivotChoosingCase.MEDIAN_OF_THREE));
    }

    public long calcComparisons(int[] array, PivotChoosingCase mode) {
        countComparisons = 0L;
        this.mode = mode;
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
        int pivot = providePivot(array, left, right);
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

    private int providePivot(int[] array, int left, int right) {
        switch (mode) {
            case FIRST:
                break;
            case LAST:
                swap(array, left, right);
                break;
            case MEDIAN_OF_THREE:
                int medianPosition = (int) Math.floor((right+left)/2);
                int middle = AlgoUtils.getIthElement(2, array[left], array[right], array[medianPosition]);
                if (array[right] == middle) {
                    swap(array, left, right);
                } else if (array[medianPosition] == middle) {
                    swap(array, left, medianPosition);
                }
                break;
        }
        return array[left];
    }

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
