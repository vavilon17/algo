package com.prinston2;

import org.junit.Before;

import java.io.File;

public class Prinston2Test {

    @Before
    public void setup() {
    }

    protected File getPrinston2File(String path) {
        return new File("src/test/resources/com/prinston2/" + path);
    }
}
