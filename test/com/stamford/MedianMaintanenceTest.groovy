package com.stamford

import com.stamford.common.AlgoUtils
import com.stamford.other.MedianMaintanence
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 18.08.13
 */
@RunWith(JUnit4.class)
class MedianMaintanenceTest {

    MedianMaintanence medianMaintanence = new MedianMaintanence()

    @Test
    public void mainTest() {
        String fileName = "resources/stamford/test/ass6/mm/ex1"
        int[] arr = AlgoUtils.readIntArray(fileName)
        assert medianMaintanence.calcMedians(arr) == 17

        fileName = "resources/stamford/test/ass6/mm/ex2"
        arr = AlgoUtils.readIntArray(fileName)
        assert medianMaintanence.calcMedians(arr) == 54

        fileName = "resources/stamford/test/ass6/mm/ex3"
        arr = AlgoUtils.readIntArray(fileName)
        assert medianMaintanence.calcMedians(arr) == 55
    }
}
