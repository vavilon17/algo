package com.stamford

import com.stamford.common.AlgoUtils
import com.stamford.other.twosum.NaiveImpl
import com.stamford.other.twosum.TwoSum
import com.sun.xml.internal.messaging.saaj.soap.name.NameImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 17.08.13
 */
@RunWith(JUnit4.class)
class TwoSumTest {

    NaiveImpl naive = new NaiveImpl();

    @Test
    public void algoTest() {
        String fileName = "resources/stamford/test/ass6/readarray/ex1"
        TwoSum program = new TwoSum(1, 5)
        assert program.calc(AlgoUtils.readUniqueLongArray(fileName)) == 3

        fileName = "resources/stamford/test/ass6/calc/ex1"
        program = new TwoSum(-10000, 10000)
        assert program.calc(AlgoUtils.readUniqueLongArray(fileName)) == 3

        fileName = "resources/stamford/test/ass6/calc/ex2"
        program = new TwoSum(-10000, 10000)
        assert program.calc(AlgoUtils.readUniqueLongArray(fileName)) == 5
    }

    @Test
    public void naiveTest() {
        String fileName = "resources/stamford/test/ass6/calc/ex1"
        long[] arr = AlgoUtils.readLongArray(fileName)
        assert naive.calc(arr) == 3

        fileName = "resources/stamford/test/ass6/calc/ex2"
        arr = AlgoUtils.readLongArray(fileName)
        assert naive.calc(arr) == 5
    }
}
