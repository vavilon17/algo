package com.prinston.collinear

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by vit on 10.07.2014.
 */
@RunWith(JUnit4.class)
class CollinearTest {

    @Test
    public void bruteTest() {
        println("TEST BRUTE.java")
        println "--testing input6.txt--"
        Brute.main("resources/prinston/test/collinear/input6.txt")
        println "\n--testing input8.txt--"
        Brute.main("resources/prinston/test/collinear/input8.txt")
    }

    @Test
    public void fastTest() {
        println("TEST FAST.java")
        Fast.main("resources/prinston/test/collinear/input6.txt")
        println "\n--testing input8.txt--"
        Fast.main("resources/prinston/test/collinear/input8.txt")
    }

}
