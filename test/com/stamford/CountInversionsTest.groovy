package com.stamford

import com.stamford.inversions.CountInversions
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by YaskalVV on 11.07.13.
 */
@RunWith(JUnit4.class)
class CountInversionsTest {

    private CountInversions countInversions = new CountInversions();

    @Test
    public void testMergeSort() {
        int[] a = [1, 5, 3, 2, 4]
        assert sort(a).toList() == [1, 2, 3, 4, 5]

        a = [1, 10, 11, 1435, 3, 8, 6, 34, 122, 345, 55, 18, 22]
        assert sort(a).toList() == [1, 3, 6, 8, 10, 11, 18, 22, 34, 55, 122, 345, 1435]

        a = [4, 4, 4]
        assert sort(a) == [4, 4, 4]

        a = [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
        assert sort(a) == [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }

    @Test
    public void testCountInversions() {
        int[] a = [1, 2, 3]
        assert countInversions.countInversions(a) == 0;

        a = [3, 2, 1]
        assert countInversions.countInversions(a) == 3;

        a = [1, 3, 5, 2, 4, 6]
        assert countInversions.countInversions(a) == 3;

        a = [4, 3, 2, 1]
        assert countInversions.countInversions(a) == 6;

        a = [5, 4, 3, 2, 1]
        assert countInversions.countInversions(a) == 10;

        a = [9, 1, 2, 3, 4, 5, 8, 7, 6]
        assert countInversions.countInversions(a) == 11;
    }

    private int[] sort(int[] input) {
        countInversions.mergeSort(input)
    }

}
