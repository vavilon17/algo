package com.prinston.collinear;

import edu.princeton.cs.algs4.In;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Ignore
@RunWith(JUnit4.class)
public class CollinearTest {

    @Test
    public void bruteTest() {
        BruteCollinearPoints brute = new BruteCollinearPoints(readPoints("input8.txt"));
        assertEquals(2, brute.numberOfSegments());
        LineSegment[] segments = brute.segments();
        assertEquals("(10000, 0) -> (0, 10000)", segments[0].toString());
        assertEquals("(3000, 4000) -> (20000, 21000)", segments[1].toString());
    }

    @Test
    public void fastTest1() {
        FastCollinearPoints brute = new FastCollinearPoints(readPoints("input6.txt"));
        assertEquals(1, brute.numberOfSegments());
        LineSegment[] segments = brute.segments();
        assertEquals("(14000, 10000) -> (32000, 10000)", segments[0].toString());
    }

    @Test
    public void fastTest2() {
        FastCollinearPoints brute = new FastCollinearPoints(readPoints("input8.txt"));
        assertEquals(2, brute.numberOfSegments());
        LineSegment[] segments = brute.segments();
        Set<String> correct = new HashSet<>(2);
        correct.add("(10000, 0) -> (0, 10000)");
        correct.add("(3000, 4000) -> (20000, 21000)");
        for (int i = 0; i < segments.length; i++) {
            assertTrue(correct.contains(segments[i].toString()));
            correct.remove(segments[i].toString());
        }
        assertTrue(correct.isEmpty());
    }

    private Point[] readPoints(String fileName) {
        In in = new In("resources/prinston/test/collinear/" + fileName);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        return points;
    }

}
