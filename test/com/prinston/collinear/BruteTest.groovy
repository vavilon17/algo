package com.prinston.collinear

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by vit on 10.07.2014.
 */
@RunWith(JUnit4.class)
class BruteTest {

    @Test
    public void bruteTest() {
        println "--testing input6.txt--"
        Brute.main("resources/prinston/test/collinear/input6.txt")
        println "\n--testing input8.txt--"
        Brute.main("resources/prinston/test/collinear/input8.txt")
        Thread.sleep(20000)
    }

}
