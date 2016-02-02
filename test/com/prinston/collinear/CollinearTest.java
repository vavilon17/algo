package com.prinston.collinear;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by vit on 10.07.2014.
 */
@RunWith(JUnit4.class)
public class CollinearTest {

    @Test
    public void bruteTest() {
//        invokeMethod("println", new Object[]{"TEST BRUTE.java"});
//        invokeMethod("println", new Object[]{"--testing input6.txt--"});
        Brute.main(new String[]{"resources/prinston/test/collinear/input6.txt"});
//        invokeMethod("println", new Object[]{"\n--testing input8.txt--"});
        Brute.main(new String[]{"resources/prinston/test/collinear/input8.txt"});
    }

    @Test
    public void fastTest() {
//        invokeMethod("println", new Object[]{"TEST FAST.java"});
        Fast.main(new String[]{"resources/prinston/test/collinear/input6.txt"});
//        invokeMethod("println", new Object[]{"\n--testing input8.txt--"});
        Fast.main(new String[]{"resources/prinston/test/collinear/input8.txt"});
    }

}
