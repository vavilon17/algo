package com.stamford

import com.stamford.common.AlgoUtils;
import com.stamford.quicksort.QuickSort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * User: YaskalVV
 * Date: 18.07.13
 */
@RunWith(JUnit4.class)
public class QuickSortTest {

    private QuickSort quickSort = new QuickSort();

    @Test
    public void testSwap() {
        int[] a = [1, 2, 3, 4]
        assert swap(a, 1, 2).toList() == [1, 3, 2, 4]

        a = [3, 4]
        assert swap(a, 0, 1).toList() == [4, 3]

        a = [1, 2]
        assert swap(a, 1, 1).toList() == [1, 2]

        a = [2, 4, 6, 1, 8]
        assert swap(a, 0, 4).toList() == [8, 4, 6, 1, 2]
    }

    @Test
    public void testSort() {
        int[] a = [1, 2, 4, 3]
        assert sort(a).toList() == [1, 2, 3, 4]

        a = [4, 3, 1, 2]
        assert sort(a).toList() == [1, 2, 3, 4]

        a = [12, 567, 1, 6, 16, 893, 7, 3, 44]
        assert sort(a).toList() == [1, 3, 6, 7, 12, 16, 44, 567, 893]

        a = [1, 10, 11, 1435, 3, 8, 6, 34, 122, 345, 55, 18, 22]
        assert sort(a).toList() == [1, 3, 6, 8, 10, 11, 18, 22, 34, 55, 122, 345, 1435]
    }

    @Test
    public void testCalcComparisons() {
        // With the pivot as a first element
        int[] a = AlgoUtils.readIntArray("resources/stamford/test/ass2/10")
        assert calcComparisons(a, QuickSort.PivotChoosingCase.FIRST) == 25L;

        a = AlgoUtils.readIntArray("resources/stamford/test/ass2/100")
        assert calcComparisons(a, QuickSort.PivotChoosingCase.FIRST) == 615L;

        a = AlgoUtils.readIntArray("resources/stamford/test/ass2/1000")
        assert calcComparisons(a, QuickSort.PivotChoosingCase.FIRST) == 10297L;

        // With the pivot as a last element
        a = AlgoUtils.readIntArray("resources/stamford/test/ass2/10")
        assert calcComparisons(a, QuickSort.PivotChoosingCase.LAST) == 29L;

        a = AlgoUtils.readIntArray("resources/stamford/test/ass2/100")
        assert calcComparisons(a, QuickSort.PivotChoosingCase.LAST) == 587L;

        a = AlgoUtils.readIntArray("resources/stamford/test/ass2/1000")
        assert calcComparisons(a, QuickSort.PivotChoosingCase.LAST) == 10184L;

        // With the pivot as a last element
        a = AlgoUtils.readIntArray("resources/stamford/test/ass2/10")
        assert calcComparisons(a, QuickSort.PivotChoosingCase.MEDIAN_OF_THREE) == 21L;

        a = AlgoUtils.readIntArray("resources/stamford/test/ass2/100")
        assert calcComparisons(a, QuickSort.PivotChoosingCase.MEDIAN_OF_THREE) == 518L;

        a = AlgoUtils.readIntArray("resources/stamford/test/ass2/1000")
        assert calcComparisons(a, QuickSort.PivotChoosingCase.MEDIAN_OF_THREE) == 8921L;
    }

    @Test
    public void testSelection() {
        assert AlgoUtils.getIthElement(1, 3, 2, 4) == 2
        assert AlgoUtils.getIthElement(3, 3, 2, 4, 6, 9, 1) == 3
        assert AlgoUtils.getIthElement(4, 18, 17, 4, 11, 121, 6) == 17
        assert AlgoUtils.getIthElement(2, 5, 6, 8) == 6
    }

    private long calcComparisons(int[] unsortedArray, QuickSort.PivotChoosingCase mode) {
        return quickSort.calcComparisons(unsortedArray, mode);
    }

    private int[] sort(int[] unsortedArray) {
        return quickSort.startQuickSort(unsortedArray);
    }

    private int[] swap(int[] array, int i, int j) {
        quickSort.swap(array, i, j);
        return array;
    }
}