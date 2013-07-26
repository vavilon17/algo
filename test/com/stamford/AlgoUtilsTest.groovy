package com.stamford

import com.stamford.common.AlgoUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * User: YaskalVV
 * Date: 25.07.13
 */
@RunWith(JUnit4.class)
class AlgoUtilsTest {

    @Test
    public void transformSpacedLineTest() {
        String spacedLine = "1   3  4       5 6 7    1   44  234  0 -1   -16"
        assert AlgoUtils.transformLineWithSpaces(spacedLine).toList() == [1, 3, 4, 5, 6, 7, 1, 44, 234, 0, -1, -16]

        spacedLine = "3"
        assert AlgoUtils.transformLineWithSpaces(spacedLine).toList() == [3]

        spacedLine = "3   4         1234 -800"
        assert AlgoUtils.transformLineWithSpaces(spacedLine).toList() == [3, 4, 1234, -800]
    }
}
