package com.prinston.collinear

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 15.09.13
 */
@RunWith(JUnit4.class)
class PointTest {

    @Test
    public void compareTest() {
        Point p1 = new Point(1, 2)
        Point p2 = new Point(2, 3)
        assert p1.compareTo(p2) < 0
        p2 = new Point(1, 3)
        assert p1.compareTo(p2) < 0
        p2 = new Point(3, 2)
        assert p1.compareTo(p2) < 0
    }

    @Test
    public void slopeToTest() {
        Point p = new Point(300, 344)
        Point q = new Point(300, 177)
        assert p.slopeTo(q) == Double.POSITIVE_INFINITY
    }
}
